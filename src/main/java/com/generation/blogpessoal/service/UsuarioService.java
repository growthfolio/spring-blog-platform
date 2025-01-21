package com.generation.blogpessoal.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.UsuarioLogin;
import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.repository.UsuarioRepository;
import com.generation.blogpessoal.security.JwtService;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public UsuarioService(UsuarioRepository usuarioRepository, JwtService jwtService,
						  AuthenticationManager authenticationManager) {
		this.usuarioRepository = usuarioRepository;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}

	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {
		// Verifica se o email já está em uso
		if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
			return Optional.empty();
		}
		// Criptografa a senha antes de salvar
		usuario.setSenha(criptografarSenha(usuario.getSenha()));
		return Optional.of(usuarioRepository.save(usuario));
	}

	public Optional<Usuario> atualizarUsuario(Usuario usuario) {
		System.out.println("=== INICIANDO ATUALIZAÇÃO DE USUÁRIO ===");
		System.out.println("Payload recebido: " + usuario);

		// Busca o usuário existente
		Usuario usuarioExistente = usuarioRepository.findById(usuario.getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

		System.out.println("Usuário existente antes da atualização: " + usuarioExistente);

		// Verifica se o email já está em uso por outro usuário
		if (usuarioRepository.findByUsuario(usuario.getUsuario())
				.filter(u -> !u.getId().equals(usuario.getId()))
				.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já está em uso!");
		}

		// Atualiza os dados básicos
		usuarioExistente.setNome(usuario.getNome());
		usuarioExistente.setUsuario(usuario.getUsuario());
		usuarioExistente.setFoto(usuario.getFoto());
		System.out.println("Dados atualizados (nome, email, foto): " + usuarioExistente);

		// Atualiza a senha apenas se enviada e válida
		if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
			if (!usuario.getSenha().startsWith("$2a$")) { // Verifica se a senha recebida não é um hash
				System.out.println("Senha recebida não é um hash. Atualizando o hash...");
				usuarioExistente.setSenha(criptografarSenha(usuario.getSenha()));
			} else {
				System.out.println("Senha recebida já é um hash. Ignorando atualização.");
			}
		} else {
			System.out.println("Campo de senha vazio. Nenhuma alteração será feita.");
		}

		// Salva o usuário atualizado
		Usuario usuarioAtualizado = usuarioRepository.save(usuarioExistente);
		System.out.println("Usuário após a atualização: " + usuarioAtualizado);
		System.out.println("=== ATUALIZAÇÃO DE USUÁRIO FINALIZADA ===");

		return Optional.of(usuarioAtualizado);
	}

	public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {
		// Gera o Objeto de autenticação
		var credenciais = new UsernamePasswordAuthenticationToken(
				usuarioLogin.get().getUsuario(),
				usuarioLogin.get().getSenha()
		);

		// Autentica o Usuário
		Authentication authentication = authenticationManager.authenticate(credenciais);

		// Se a autenticação foi efetuada com sucesso
		if (authentication.isAuthenticated()) {

			// Busca os dados do usuário
			Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());

			// Se o usuário foi encontrado
			if (usuario.isPresent()) {

				// Preenche o Objeto usuarioLogin com os dados encontrados
				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setFoto(usuario.get().getFoto());
				usuarioLogin.get().setToken(gerarToken(usuarioLogin.get().getUsuario()));
				usuarioLogin.get().setSenha(""); // Remove a senha do objeto para segurança

				return usuarioLogin;
			}
		}

		return Optional.empty();
	}

	private String criptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);
	}

	private String gerarToken(String usuario) {
		return "Bearer " + jwtService.generateToken(usuario);
	}
}
