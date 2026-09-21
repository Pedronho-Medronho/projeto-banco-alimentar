// package com.bancoalimentar.Controller;

// import com.bancoalimentar.model.Alimento;
// import com.bancoalimentar.model.TipoAlimento;
// import com.bancoalimentar.service.AlimentoService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/alimentos")

// public class AlimentoController {

//     @Autowired
//     private AlimentoService alimentoService;

 
//     @PostMapping
//     public ResponseEntity<Alimento> criarAlimento(@RequestBody Alimento alimento) {
//         Alimento novoAlimento = alimentoService.criarAlimento(alimento);
//         return ResponseEntity.ok(novoAlimento);
//     }

  
//     @GetMapping
//     public ResponseEntity<List<Alimento>> listarAlimentos() {
//         return ResponseEntity.ok(alimentoService.listarAlimentos());
//     }

 
//     @GetMapping("/{id}")
//     public ResponseEntity<Alimento> buscarAlimentoPorId(@PathVariable Long id) {
//         return alimentoService.buscarAlimentoPorId(id)
//                 .map(ResponseEntity::ok)
//                 .orElseGet(() -> ResponseEntity.notFound().build());
//     }

  
//     @GetMapping("/tipo/{tipo}")
//     public ResponseEntity<List<Alimento>> listarAlimentosPorTipo(@PathVariable TipoAlimento tipo) {
//         return ResponseEntity.ok(alimentoService.listarAlimentosPorTipo(tipo));
//     }

//     @GetMapping("/vencidos")
//     public ResponseEntity<List<Alimento>> listarAlimentosVencidos() {
//         return ResponseEntity.ok(alimentoService.listarAlimentosVencidos());
//     }

// }