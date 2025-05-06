package app.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dto.Entrada;
import app.entity.Calculo;
import app.repository.CalculoRepository;

@Service
public class CalculoService {

	@Autowired
	private CalculoRepository calculoRepository;

	public Calculo calcular(Entrada entrada) {

		List<Integer> lista = entrada.getLista();

		Calculo calculo = new Calculo();
		calculo.setLista(lista);
		calculo.setSoma(this.soma(lista));
		calculo.setMedia(this.media(lista));
		calculo.setMediana(this.mediana(lista));
		calculo.setMaior(this.maior(lista));
		calculo.setMenor(this.menor(lista));
		calculo.setTotalElementos(this.totalElementos(lista));
		calculo.setModa(this.moda(lista));
		calculo.setVariancia(this.variancia(lista));
		calculo.setDesvioPadrao(this.desvioPadrao(lista));

		return calculo;
	}

	public int soma(List<Integer> lista) {
		int soma = 0;
		for (Integer num : lista) {
			if (num == null) throw new RuntimeException("Número nulo encontrado na lista");
			soma += num;
		}
		return soma;
	}

	public double media(List<Integer> lista) {
		if (lista == null || lista.isEmpty()) throw new IllegalArgumentException("Lista inválida");
		return soma(lista) / (double) lista.size(); // 👈 cast para evitar divisão inteira
	}

	public double mediana(List<Integer> lista) {
		if (lista == null || lista.isEmpty()) {
			throw new IllegalArgumentException("A lista não pode ser nula ou vazia");
		}
		Collections.sort(lista);
		int meio = lista.size() / 2;

		if (lista.size() % 2 == 0) {
			// 👇 corrigido para usar 2.0
			return (lista.get(meio - 1) + lista.get(meio)) / 2.0;
		} else {
			return lista.get(meio);
		}
	}

	public int maior(List<Integer> lista) {
		if (lista == null || lista.isEmpty()) {
			throw new IllegalArgumentException("A lista não pode ser nula ou vazia");
		}
		return Collections.max(lista);
	}

	public int menor(List<Integer> lista) {
		if (lista == null || lista.isEmpty()) {
			throw new IllegalArgumentException("A lista não pode ser nula ou vazia");
		}
		return Collections.min(lista);
	}

	public int totalElementos(List<Integer> lista) {
		if (lista == null) {
			throw new IllegalArgumentException("A lista não pode ser nula");
		}
		return lista.size();
	}

	public Integer moda(List<Integer> lista) {
		if (lista == null || lista.isEmpty()) {
			throw new IllegalArgumentException("A lista não pode ser nula ou vazia");
		}
		Map<Integer, Integer> frequencias = new HashMap<>();
		for (Integer num : lista) {
			frequencias.put(num, frequencias.getOrDefault(num, 0) + 1);
		}
		int maxFreq = Collections.max(frequencias.values());
		for (Map.Entry<Integer, Integer> entry : frequencias.entrySet()) {
			if (entry.getValue() == maxFreq) {
				return entry.getKey();
			}
		}
		return null;
	}

	public double variancia(List<Integer> lista) {
		double media = media(lista);
		double somaQuadrados = 0.0;
		for (Integer num : lista) {
			somaQuadrados += Math.pow(num - media, 2);
		}
		return somaQuadrados / lista.size();
	}

	public double desvioPadrao(List<Integer> lista) {
		return Math.sqrt(variancia(lista));
	}
}
