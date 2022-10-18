package br.com.residencia.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.AlunoDTO;
import br.com.residencia.biblioteca.entity.Aluno;
import br.com.residencia.biblioteca.repository.AlunoRepository;

@Service
public class AlunoService {
	@Autowired
	AlunoRepository alunoRepository;

	public List<Aluno> getAllAlunos() {
		return alunoRepository.findAll();
	}

	public Aluno getAlunoById(Integer id) {
		return alunoRepository.findById(id).orElse(null);
	}

	public Aluno saveAluno(Aluno aluno) {
		return alunoRepository.save(aluno);
	}

	// SAVEDTO

	public AlunoDTO saveAlunoDTO(AlunoDTO alunoDTO) {
		Aluno aluno = toEntidade(alunoDTO);
		Aluno novoAluno = alunoRepository.save(aluno);

		AlunoDTO alunoAtualizadoDTO = toDTO(novoAluno);
		return alunoAtualizadoDTO;
	}

	public Aluno updateAluno(Aluno aluno, Integer id) {
		// Aluno alunoExistenteNoBanco = alunoRepository.findById(id).get();

		Aluno alunoExistenteNoBanco = getAlunoById(id);

		alunoExistenteNoBanco.setBairro(aluno.getBairro()); // Centro
		alunoExistenteNoBanco.setCidade(aluno.getCidade()); // Petropolis
		alunoExistenteNoBanco.setComplemento(aluno.getComplemento()); // SN
		alunoExistenteNoBanco.setCpf(aluno.getCpf()); // 123456789
		alunoExistenteNoBanco.setDataNascimento(aluno.getDataNascimento()); // ...
		alunoExistenteNoBanco.setLogradouro(aluno.getLogradouro());
		alunoExistenteNoBanco.setNome(aluno.getNome());
		alunoExistenteNoBanco.setNumeroLogradouro(aluno.getNumeroLogradouro());

		return alunoRepository.save(alunoExistenteNoBanco);

		// return alunoRepository.save(aluno);
	}

	public AlunoDTO updateAlunoDTO(AlunoDTO alunoDTO, Integer id) {
		Aluno alunoExistenteNoBanco = getAlunoById(id);

		AlunoDTO alunoAtualizadaDTO = new AlunoDTO();

		if (alunoExistenteNoBanco != null) {
			alunoExistenteNoBanco.setNome(alunoDTO.getNome());
			Aluno alunoAtualizada = alunoRepository.save(alunoExistenteNoBanco);
			
			alunoAtualizadaDTO.setBairro(alunoAtualizada.getBairro());
			alunoAtualizadaDTO.setCidade(alunoAtualizada.getCidade());
			alunoAtualizadaDTO.setComplemento(alunoAtualizada.getComplemento());
			alunoAtualizadaDTO.setCpf(alunoAtualizada.getCpf());
			alunoAtualizadaDTO.setDataNascimento(alunoAtualizada.getDataNascimento());
			// alunoAtualizadaDTO.setEmprestimos(alunoAtualizada.getEmprestimos());
			alunoAtualizadaDTO.setLogradouro(alunoAtualizada.getLogradouro());
			alunoAtualizadaDTO.setNome(alunoAtualizada.getNome());
			alunoAtualizadaDTO.setNumeroMatriculaAluno(alunoAtualizada.getNumeroMatriculaAluno());
			
		}
		return alunoAtualizadaDTO;
	}

	// Metodo toEntidade

	private Aluno toEntidade(AlunoDTO alunoDTO) {
		Aluno aluno = new Aluno();

		aluno.setBairro(alunoDTO.getBairro());
		aluno.setCidade(alunoDTO.getCidade());
		aluno.setComplemento(alunoDTO.getComplemento());
		aluno.setCpf(alunoDTO.getCpf());
		aluno.setDataNascimento(alunoDTO.getDataNascimento());
		// aluno.setEmprestimos(alunoDTO.getEmprestimos());
		aluno.setLogradouro(alunoDTO.getLogradouro());
		aluno.setNome(alunoDTO.getNome());
		aluno.setNumeroMatriculaAluno(alunoDTO.getNumeroMatriculaAluno());

		return aluno;
	}

	// Metodo toDTO
	private AlunoDTO toDTO(Aluno aluno) {
		AlunoDTO alunoDTO = new AlunoDTO();

		alunoDTO.setBairro(aluno.getBairro());
		alunoDTO.setCidade(aluno.getCidade());
		alunoDTO.setComplemento(aluno.getComplemento());
		alunoDTO.setCpf(aluno.getCpf());
		alunoDTO.setDataNascimento(aluno.getDataNascimento());
		// alunoDTO.setEmprestimos(aluno.getEmprestimos());
		alunoDTO.setLogradouro(aluno.getLogradouro());
		alunoDTO.setNome(aluno.getNome());

		return alunoDTO;
	}

	public Aluno deleteAluno(Integer id) {
		alunoRepository.deleteById(id);
		return getAlunoById(id);
	}

}