package com.mballem.tarefas.web.controller;

import com.mballem.internal.entity.Contato;
import com.mballem.internal.service.ContatoService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("tarefas/contatos")
public class ContatoController {

    private final ContatoService contatoService;
    // EXERCICIO 1
    @PostMapping
    public ResponseEntity<Contato> create(@RequestBody Contato contato) {
        Contato cont = contatoService.save(contato);
        return ResponseEntity.status(HttpStatus.CREATED).body(cont);
    }

    // EXERCICIO 2
    @GetMapping(value = "/{id}")
    public ResponseEntity<Contato> getContatoById(@PathVariable Long id) {
        Contato contato = contatoService.getById(id);
        return ResponseEntity.ok(contato);
    }

    // EXERCICIO 3
    @GetMapping(value = "/name")
    public ResponseEntity<Contato> getContatoByNome(@RequestParam(value="name", defaultValue="") String text) {
        text = URLDecoder.decode(text, StandardCharsets.UTF_8);
        Contato contato = contatoService.getContatoByNome(text);
        return ResponseEntity.ok(contato);
    }

    // EXERCICIO 4
    @GetMapping
    public ResponseEntity<Integer> getQuantidadeDeContatos() {
        int quantidade = contatoService.getAll();
        return ResponseEntity.ok(quantidade);
    }

    // EXERCICIO 5
    @GetMapping(value = "/data")
    public ResponseEntity<List<Contato>> getContatosByDataNascimento(@RequestParam(value="data", defaultValue="") LocalDate data) {
        List<Contato> contato = contatoService.getByDataNascimento(data);
        return ResponseEntity.ok(contato);
    }

    // EXERCICIO 6
    @PatchMapping("/{id}")
    public ResponseEntity<Contato> updateContatoById(@PathVariable Long id, @RequestBody Contato nome) {
        Contato contato = contatoService.update(id, nome);
        return ResponseEntity.ok(contato);
    }

    // EXERCICIO 7
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        String mensagem = contatoService.delete(id);
        return ResponseEntity.ok(mensagem);
    }
}
