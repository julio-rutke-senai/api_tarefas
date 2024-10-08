package rutke.julio.tarefas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import rutke.julio.tarefas.entities.Tarefa;
import rutke.julio.tarefas.entities.Usuario;
import rutke.julio.tarefas.entities.dtos.AtualizarSenhaDTO;
import rutke.julio.tarefas.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	private UsuarioRepository usuarioRepository;
	private PasswordEncoder passwordEncoder;
	
	public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public Usuario criarUsuario(Usuario usuario) {
		String pass = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(pass);
		usuarioRepository.save(usuario);
		return usuario;
	}
	
	public Usuario atualizarUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
		return usuario;
	}
	
	public Usuario atualizarSenhaUsuario(AtualizarSenhaDTO senhaUsuario) {
		Optional<Usuario> usuario = usuarioRepository.findById(senhaUsuario.getCodigo());
		String pass = passwordEncoder.encode(senhaUsuario.getSenha());
		usuario.get().setSenha(pass);
		usuarioRepository.save(usuario.get());
		return usuario.get();
	}
	
	public List<Usuario> listarUsuarios(){
		return usuarioRepository.findAll();
	}
	
	public Optional<Usuario> listarUsuarioPorCodigo(Long codigo) { 
		Optional<Usuario> usuario = usuarioRepository.findById(codigo);
		return usuario;
	}
	
	public void excluirUsuario(Long codigo) {
		usuarioRepository.deleteById(codigo);
	}	
	
	public Optional<Usuario> getUsuarioAutenticacao(String username){
		return usuarioRepository.findByEmail(username);
	}

}
