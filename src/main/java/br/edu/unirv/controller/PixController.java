package br.edu.unirv.controller;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unirv.model.Cobranca;
import br.edu.unirv.repository.CobrancaRepository;

@RestController
public class PixController {
	
	@Autowired
	private CobrancaRepository cobrancaRepository;
	
	@PostMapping("/cob")
	public ResponseEntity<Cobranca> addCobranca(@RequestBody Cobranca cobranca) {
		HttpStatus respostaValidacao = validarDados(cobranca);
		if(respostaValidacao == HttpStatus.OK) {
			cobrancaRepository.save(cobranca);
			return new ResponseEntity<>(cobranca, HttpStatus.OK);
		}
		return new ResponseEntity<Cobranca>(respostaValidacao);
	}
	
	@GetMapping("/cob/{txid}")
	public ResponseEntity<Cobranca> findByTxid(@PathVariable(value= "txid") String txid) {
		Cobranca cobranca = cobrancaRepository.findByTxid(txid);
		if(cobranca != null) {
			return new ResponseEntity<Cobranca>(cobranca, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/cobs")
	public ResponseEntity<List<Cobranca>> findAll() {
		return new ResponseEntity<List<Cobranca>>(cobrancaRepository.findAll(), HttpStatus.OK);
	}
	
	public HttpStatus validarDados(Cobranca cobranca) {

		if(cobranca.getTxid().isEmpty() ||  (cobranca.getTxid().length() < 1 && cobranca.getTxid().length() > 35)) {
			return HttpStatus.NOT_ACCEPTABLE;
		}
		if(cobranca.getChave().length() > 77) {
			return HttpStatus.NOT_ACCEPTABLE;
		}
		if(cobranca.getCpf() != null) {
			if(cobranca.getCpf().length() !=  11) {
				return HttpStatus.NOT_ACCEPTABLE;
			}
			Pattern pattern = Pattern.compile("[A-Z]");
			Matcher matcher = pattern.matcher(cobranca.getCpf());
			if(matcher.find()) {
				return HttpStatus.NOT_ACCEPTABLE;
			}		
		}
		if(cobranca.getCnpj() != null) {
			if(cobranca.getCnpj().length() !=  14) {
				return HttpStatus.NOT_ACCEPTABLE;
			}
			Pattern pattern = Pattern.compile("[A-Z]");
			Matcher matcher = pattern.matcher(cobranca.getCnpj());
			if(matcher.find()) {
				return HttpStatus.NOT_ACCEPTABLE;
			}		
		}
		if(cobranca.getNome() != null) {
			if(cobranca.getNome().length() > 200) {
				return HttpStatus.NOT_ACCEPTABLE;
			}	
		}
		if(cobranca.getValorOriginal() == null) {
			return HttpStatus.NOT_ACCEPTABLE;
		}else{
			Pattern pattern = Pattern.compile("[A-Z]");
			Matcher matcher = pattern.matcher(cobranca.getValorOriginal());
			if(matcher.find()) {
				return HttpStatus.NOT_ACCEPTABLE;
			}else if(Integer.parseInt(cobranca.getValorOriginal()) <= 0) {
				return HttpStatus.NOT_ACCEPTABLE;
			}
		}
		if(cobranca.getSolicitacaoPagador() != null) {
			if(cobranca.getSolicitacaoPagador().length() > 140) {
				return HttpStatus.NOT_ACCEPTABLE;
			}	
		}
		if(cobranca.getStatus().length() > 31) {
			return HttpStatus.NOT_ACCEPTABLE;
		}
		
		return HttpStatus.OK;
	}
	
}
