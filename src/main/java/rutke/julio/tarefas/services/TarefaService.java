package rutke.julio.tarefas.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import rutke.julio.tarefas.entities.Tarefa;

@Service
public class TarefaService {
	
	private List<Tarefa> tarefas;
	
	public TarefaService() {
		tarefas = new ArrayList<>();
	}
	
	public Tarefa criarTarefa(Tarefa tarefa) {
		tarefas.add(tarefa);
		return tarefa;
	}
	
	public Tarefa atualizarTarefa(Tarefa tarefa) {
		tarefas.stream().forEach(t -> {
			if(t.getCodigo() == tarefa.getCodigo()) {
				t.setDescricao(tarefa.getDescricao());
				t.setStatus(tarefa.getStatus());
			}
		});
		
		return tarefa;
	}
	
	public List<Tarefa> listarTarefas(){
		return tarefas;
	}
	
	public Tarefa listarTarefaPorCodigo(Long codigo) { 
		Optional<Tarefa> tarefa = tarefas.stream().filter(t -> t.getCodigo() == codigo).findFirst();
		if(Optional.ofNullable(tarefa).isPresent())
			return tarefa.get();
		else
			return new Tarefa();
	}
	
	public void excluirTarefa(Long codigo) {
		tarefas.removeIf(p -> p.getCodigo() == codigo);
	}

	public void atualizarStatusTarefa(Long codigo, String status) {
		tarefas.stream().forEach(t -> {
			if(t.getCodigo() == codigo) {
				t.setStatus(status);
			}
		});
	}
	
	

}
