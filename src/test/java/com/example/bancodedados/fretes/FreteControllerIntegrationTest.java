package com.example.bancodedados.fretes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.bancodedados.cidade.Cidade;
import com.example.bancodedados.cidade.CidadeRepository;
import com.example.bancodedados.cliente.Cliente;
import com.example.bancodedados.cliente.ClienteRepository;
import com.example.bancodedados.frete.Frete;
import com.example.bancodedados.frete.FreteException;
import com.example.bancodedados.frete.FreteRepository;
import com.example.bancodedados.frete.FreteService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FreteControllerIntegrationTest {
    
        // serve para consumir os métodos HTTP
        @Autowired
        private TestRestTemplate testRestTemplate;
        @Autowired
        private ClienteRepository clienteRepository;
        @Autowired
        private CidadeRepository cidadeRepository;
        @Autowired
        private FreteService freteService;

        @Autowired
        private FreteRepository freteRepository;

        private Frete frete;
        private Frete frete1;
        private Frete frete2;
        private Frete frete3;
        ObjectMapper objectMapper = new ObjectMapper();

        @Before
        public void start() throws FreteException {
            Cliente cliente1 = new Cliente("Enos", "Monte Castelo", "123456789");
            clienteRepository.save(cliente1);
            Cliente cliente2 = new Cliente("Breno", "Maioba", "222123456");
            clienteRepository.save(cliente2);
            Cliente cliente3 = new Cliente("Pablo", "Bequimao", "987654321");
            clienteRepository.save(cliente3);

            Cidade cidade1 = new Cidade("Sao Luis", 12.3, "MA");
            cidadeRepository.save(cidade1);
            Cidade cidade2 = new Cidade("Rio de Janeiro", 5.6, "RJ");
            cidadeRepository.save(cidade2);
            Cidade cidade3 = new Cidade("Sao Paulo", 2.3, "SP");
            cidadeRepository.save(cidade3);


            frete1 = new Frete(234.0,"Maquina 1",1000.0,cliente2,cidade2);
            freteService.inserir(frete1);
            frete2 = new Frete(134.0,"Maquina 2",1000.0,cliente3,cidade3);
            freteService.inserir(frete2);
            frete3 = new Frete(124.0,"Maquina 4",1000.0,cliente1,cidade1);
            freteService.inserir(frete3);

            frete = new Frete(334.0,"Maquina 3",1000.0,cliente1,cidade1);
            //freteService.inserir(frete);

        }

        @After
        public void end() {
            //clienteRepository.deleteAll();
            //cidadeRepository.findAll();
            freteRepository.deleteAll();
        }


        @Test
        public void deveMostrarTodosFretes() throws IOException {

            List<Frete>  fretesCadastrados = new ArrayList<>();

            fretesCadastrados.add(frete1);
            fretesCadastrados.add(frete2);
            fretesCadastrados.add(frete3);

            ResponseEntity<String> resposta = testRestTemplate.exchange("/fretecontroler/", HttpMethod.GET, null, String.class);
            Assert.assertEquals(HttpStatus.OK, resposta.getStatusCode());

            String json = resposta.getBody();
            List<Frete> fretes = objectMapper.readValue(json, new TypeReference<List<Frete>>(){});

            Assert.assertEquals(fretesCadastrados.size(), fretes.size());
            fretesCadastrados.forEach( f -> {
                Assert.assertTrue(fretes.contains(f));
            });

        }

    @Test
    public void deveMostrarUmFrete() throws IOException {

        ResponseEntity<Frete> resposta =
                testRestTemplate.exchange("/fretecontroler/frete/{id}",HttpMethod.GET,null, Frete.class, frete1.getId());
        Assert.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Assert.assertTrue(resposta.getHeaders().getContentType().equals(MediaType.parseMediaType("application/json;charset=UTF-8")));

        Assert.assertEquals(frete1, resposta.getBody());
    }

    @Test
    public void buscaUmFreteDeveRetornarNaoEncontrado() {

        ResponseEntity<Frete> resposta =
                testRestTemplate.exchange("/fretecontroler/frete/{id}",HttpMethod.GET,null, Frete.class,100 );

        Assert.assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
        Assert.assertNull(resposta.getBody());
    }

    @Test
    public void inserirDeveSalvarFrete() {

        HttpEntity<Frete> httpEntity = new HttpEntity<>(frete);

        ResponseEntity<Frete> resposta =
                testRestTemplate.exchange("/fretecontroler/inserir",HttpMethod.POST,httpEntity, Frete.class);

        Assert.assertEquals(HttpStatus.CREATED,resposta.getStatusCode());

        Frete resultado = resposta.getBody();

        Assert.assertNotNull(resultado.getId());
        Assert.assertTrue(frete.getValor() == resultado.getValor());
        Assert.assertEquals(frete.getCliente(), resultado.getCliente());
        Assert.assertEquals(frete.getCidade(), resultado.getCidade());

        freteService.remover(resultado.getId());
    }

    @Test
    public void salvarFreteDeveRetornarMensagemDeErro() {
        frete.setCidade(null);
        HttpEntity<Frete> httpEntity = new HttpEntity<>(frete);

        ResponseEntity<String> resposta =
                testRestTemplate.exchange("/fretecontroler/inserir",
                        HttpMethod.POST,httpEntity,
                        new ParameterizedTypeReference<String>() {});

        Assert.assertEquals(HttpStatus.BAD_REQUEST,resposta.getStatusCode());
        Assert.assertTrue(resposta.getBody().contains("A cidade deve ser preenchida"));
    }


    @Test
    public void alterarDeveAlterarFrete() {
        frete1.setDescricao("Produto fragil");
        HttpEntity<Frete> httpEntity = new HttpEntity<>(frete1);
        ResponseEntity<Frete> resposta =
                testRestTemplate.exchange("/fretecontroler/alterar/{id}",HttpMethod.PUT,httpEntity,Frete.class,frete1.getId());

        Assert.assertEquals(HttpStatus.CREATED,resposta.getStatusCode());
        Frete resultado = resposta.getBody();
        Assert.assertEquals(frete1.getId(), resultado.getId());
        Assert.assertEquals("Produto fragil", resultado.getDescricao());
    }
    @Test
    public void alterarFreteDeveRetornarMensagemDeErro() {
        frete1.setCidade(null);
        frete1.setCliente(null);
        HttpEntity<Frete> httpEntity = new HttpEntity<>(frete1);
        ResponseEntity<String> resposta =
                testRestTemplate.exchange("/fretecontroler/alterar/{id}",HttpMethod.PUT
                        ,httpEntity, new ParameterizedTypeReference<String>() {},frete1.getId());

        Assert.assertEquals(HttpStatus.BAD_REQUEST,resposta.getStatusCode());
        Assert.assertTrue(resposta.getBody().contains("A cidade deve ser preenchida"));
        Assert.assertTrue(resposta.getBody().contains("O cliente deve ser preenchido"));
    }
    @Test
    public void removerDeveExcluirFrete() {

        Long idRemove = frete2.getId();
        ResponseEntity<Frete> resposta =
                testRestTemplate.exchange("/fretecontroler/remover/{id}",HttpMethod.DELETE,null, Frete.class,idRemove);
        Assert.assertEquals(HttpStatus.NO_CONTENT,resposta.getStatusCode());
        Assert.assertNull(resposta.getBody());

        ResponseEntity<Frete> resposta2 =
                testRestTemplate.exchange("/fretecontroler/frete/{id}",HttpMethod.GET,null, Frete.class,idRemove );

        Assert.assertEquals(HttpStatus.NOT_FOUND, resposta2.getStatusCode());
        Assert.assertNull(resposta2.getBody());
    }

}
