package com.workercompras.service;

import com.workercompras.model.Endereco;
import com.workercompras.repository.CepRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CepService {

    @Autowired
    CepRepository cepRepository;

    public void buscarCep(String cep){
       Endereco endereco = cepRepository.buscarPorCep(cep);
       log.info("Endereço montado com sucesso: {}", endereco);
    }

}
