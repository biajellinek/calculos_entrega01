package app.service.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import app.dto.Entrada;
import app.service.CalculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculoServiceTest {

    private CalculoService calculoService;

    @BeforeEach
    void setup() {
        this.calculoService = new CalculoService(); // instância direta
    }

    @Test
    @DisplayName("Soma de valores válidos")
    void testSomaValoresValidos() {
        List<Integer> lista = Arrays.asList(3, 5, 2);
        assertEquals(10, calculoService.soma(lista));
    }

    @Test
    @DisplayName("Soma com valor nulo na lista")
    void testSomaComNulo() {
        List<Integer> lista = Arrays.asList(1, null, 3);
        assertThrows(RuntimeException.class, () -> calculoService.soma(lista));
    }

    @Test
    @DisplayName("Mediana com número ímpar")
    void testMedianaImpar() {
        List<Integer> lista = Arrays.asList(3, 1, 2);
        assertEquals(2.0, calculoService.mediana(lista));
    }

    @Test
    @DisplayName("Mediana com número par")
   
    public double mediana(List<Integer> numeros) {
        if (numeros == null || numeros.isEmpty()) {
            throw new IllegalArgumentException("Lista não pode ser nula ou vazia");
        }

        List<Integer> ordenada = numeros.stream()
                                        .sorted()
                                        .toList();

        int tamanho = ordenada.size();
        if (tamanho % 2 == 1) {
            return ordenada.get(tamanho / 2);
        } else {
            return (ordenada.get(tamanho / 2 - 1) + ordenada.get(tamanho / 2)) / 2.0;
        }
    }


    @Test
    @DisplayName("Mediana com lista vazia")
    void testMedianaListaVazia() {
        assertThrows(IllegalArgumentException.class, () -> calculoService.mediana(Collections.emptyList()));
    }

    @Test
    @DisplayName("Teste de média")
    void testMedia() {
        List<Integer> lista = Arrays.asList(2, 4, 6);
        assertEquals(4.0, calculoService.media(lista));
    }

    @Test
    @DisplayName("Maior valor")
    void testMaior() {
        List<Integer> lista = Arrays.asList(1, 5, 3);
        assertEquals(5, calculoService.maior(lista));
    }

    @Test
    @DisplayName("Menor valor")
    void testMenor() {
        List<Integer> lista = Arrays.asList(7, 3, 9);
        assertEquals(3, calculoService.menor(lista));
    }

    @Test
    @DisplayName("Total de elementos")
    void testTotalElementos() {
        List<Integer> lista = Arrays.asList(1, 2, 3, 4);
        assertEquals(4, calculoService.totalElementos(lista));
    }

    @Test
    @DisplayName("Moda com valor mais frequente")
    void testModa() {
        List<Integer> lista = Arrays.asList(1, 2, 2, 3, 3, 3);
        assertEquals(3, calculoService.moda(lista));
    }

    @Test
    @DisplayName("Moda com lista vazia")
    void testModaListaVazia() {
        assertThrows(IllegalArgumentException.class, () -> calculoService.moda(Collections.emptyList()));
    }

    @Test
    @DisplayName("Desvio padrão positivo")
    void testDesvioPadrao() {
        List<Integer> lista = Arrays.asList(10, 12, 23, 23, 16, 23, 21, 16);
        assertTrue(calculoService.desvioPadrao(lista) > 0);
    }

    @Test
    @DisplayName("Variância positiva")
    void testVariancia() {
        List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5);
        assertTrue(calculoService.variancia(lista) > 0);
    }

    @Test
    @DisplayName("Cálculo completo da Entrada")
    void testCalcularResultadoCompleto() {
        List<Integer> lista = Arrays.asList(1, 2, 2, 3, 4);
        Entrada entrada = new Entrada();
        entrada.setLista(lista);

        var resultado = calculoService.calcular(entrada);

        assertEquals(12, resultado.getSoma());
        assertEquals(2.4, resultado.getMedia());
        assertEquals(2.0, resultado.getMediana());
        assertEquals(4, resultado.getMaior());
        assertEquals(1, resultado.getMenor());
        assertEquals(5, resultado.getTotalElementos());
        assertEquals(2, resultado.getModa());
        assertNotNull(resultado.getDesvioPadrao());
        assertNotNull(resultado.getVariancia());
    }
}
