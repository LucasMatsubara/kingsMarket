package com.kingsMarket.KingsMarket.service;

import com.kingsMarket.KingsMarket.model.JogoModel;
import com.kingsMarket.KingsMarket.model.DesenvolvedoraModel;
import com.kingsMarket.KingsMarket.repository.JogoRepository;
import com.kingsMarket.KingsMarket.repository.DesenvolvedoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JogoService {

    @Autowired
    private JogoRepository repository;

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

    public JogoModel salvar(JogoModel jogo) {
        if (jogo.getDesenvolvedora() != null && jogo.getDesenvolvedora().getId() != null) {
            DesenvolvedoraModel dev = desenvolvedoraRepository.findById(jogo.getDesenvolvedora().getId())
                    .orElseThrow(() -> new RuntimeException("Desenvolvedora não encontrada"));
            jogo.setDesenvolvedora(dev);
        }
        return repository.save(jogo);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}