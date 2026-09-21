// package com.bancoalimentar.Controller;

// import com.bancoalimentar.model.Armazem;
// import com.bancoalimentar.model.AreaArmazenamento;
// import com.bancoalimentar.service.ArmazemService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/armazens")
// public class ArmazemController {

//     @Autowired
//     private ArmazemService service;

//     @PostMapping
//     public ResponseEntity<Armazem> criar(@RequestBody Armazem armazem) {
//         return ResponseEntity.ok(service.criar(armazem));
//     }

//     @GetMapping
//     public ResponseEntity<List<Armazem>> listar() {
//         return ResponseEntity.ok(service.listar());
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Armazem> buscarPorId(@PathVariable Long id) {
//         return service.buscarPorId(id)
//                 .map(ResponseEntity::ok)
//                 .orElseGet(() -> ResponseEntity.notFound().build());
//     }

//     @PostMapping("/{id}/areas")
//     public ResponseEntity<Void> adicionarArea(@PathVariable Long id, @RequestBody AreaArmazenamento area) {
//         service.adicionarArea(id, area);
//         return ResponseEntity.ok().build();
//     }
// } {
    
// }
