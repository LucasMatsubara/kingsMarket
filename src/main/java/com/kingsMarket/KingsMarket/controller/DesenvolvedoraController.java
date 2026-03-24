package com.kingsMarket.KingsMarket.controller;

import com.kingsMarket.KingsMarket.model.DesenvolvedoraModel;
import com.kingsMarket.KingsMarket.service.DesenvolvedoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/desenvolvedoras")
public class DesenvolvedoraController {

    @Autowired
    private DesenvolvedoraService service;


    @GetMapping
    public List<DesenvolvedoraModel> listar() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DesenvolvedoraModel> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DesenvolvedoraModel criar(@RequestBody DesenvolvedoraModel desenvolvedora) {
        return service.salvar(desenvolvedora);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DesenvolvedoraModel> atualizar(@PathVariable Long id, @RequestBody DesenvolvedoraModel dadosAtualizados) {
        return service.buscarPorId(id).map(desenvolvedora -> {
            desenvolvedora.setNome(dadosAtualizados.getNome());
            desenvolvedora.setDescricao(dadosAtualizados.getDescricao());
            desenvolvedora.setDataFundacao(dadosAtualizados.getDataFundacao());
            return ResponseEntity.ok(service.salvar(desenvolvedora));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.buscarPorId(id).isPresent()) {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}