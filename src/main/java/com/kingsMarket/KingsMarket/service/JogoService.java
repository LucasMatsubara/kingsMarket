package com.kingsMarket.KingsMarket.service;

import com.kingsMarket.KingsMarket.model.DesenvolvedoraModel;
import com.kingsMarket.KingsMarket.model.JogoModel;
import com.kingsMarket.KingsMarket.repository.DesenvolvedoraRepository;
import com.kingsMarket.KingsMarket.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JogoService {

    @Autowired
    private JogoRepository repository;

    // 1. Adicionamos o repositório da Desenvolvedora aqui
    @Autowired
    private DesenvolvedoraRepository desenvolvedoraRepository;

    public List<JogoModel> listarTodos() {
        return repository.findAll();
    }

    public Optional<JogoModel> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<JogoModel> buscarPorDesenvolvedora(Long id) {
        return repository.findByDesenvolvedoraId(id);
    }

    // 2. Arrumamos o método salvar
    public JogoModel salvar(JogoModel jogo) {
        if (jogo.getDesenvolvedora() != null && jogo.getDesenvolvedora().getId() != null) {
            // Busca a desenvolvedora completa no banco Oracle antes de salvar
            DesenvolvedoraModel devCompleta = desenvolvedoraRepository.findById(jogo.getDesenvolvedora().getId())
                    .orElseThrow(() -> new RuntimeException("Desenvolvedora não encontrada com este ID"));

            // Preenche o jogo com os dados completos
            jogo.setDesenvolvedora(devCompleta);
        }
        return repository.save(jogo);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}