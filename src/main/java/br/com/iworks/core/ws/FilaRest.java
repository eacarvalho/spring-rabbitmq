package br.com.iworks.core.ws;

import br.com.iworks.core.service.ProcessamentoProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filas")
public class FilaRest {

    @Autowired
    private ProcessamentoProducerService processamentoEnviarService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{filaId}", method = RequestMethod.GET)
    public ResponseEntity<String> processar(@PathVariable String filaId) {
        processamentoEnviarService.send();

        return ResponseEntity.ok().body(filaId);
    }
}