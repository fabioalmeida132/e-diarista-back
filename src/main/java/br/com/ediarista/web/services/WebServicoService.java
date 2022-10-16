package br.com.ediarista.web.services;

import br.com.ediarista.core.exceptions.ServicoNaoEncontradoException;
import br.com.ediarista.core.models.Servico;
import br.com.ediarista.core.repositories.ServicoRepository;
import br.com.ediarista.web.dtos.ServicoForm;
import br.com.ediarista.web.mappers.WebServicoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebServicoService {
    @Autowired
    private ServicoRepository repository;

    @Autowired
    private WebServicoMapper mapper;

    public List<Servico> buscarTodos(){
        return repository.findAll();
    }

    public Servico cadastrar(ServicoForm form){
        var model = mapper.toModel(form);
        return repository.save(model);
    }

    public Servico buscarPorId(Long id){
        var servicoEncontrado = repository.findById(id);
        if(servicoEncontrado.isPresent()){
            return servicoEncontrado.get();
        }

        var mensagem = String.format("Serviço com ID %d não encontrado", id);
        throw new ServicoNaoEncontradoException(mensagem);
    }

    public Servico editar (ServicoForm form, Long id){
        var servicoEncontrado = buscarPorId(id);
        var model = mapper.toModel(form);
        model.setId(servicoEncontrado.getId());

        return repository.save(model);
    }

    public void excluirPorId(Long id){
        var servicoEncontrado = buscarPorId(id);
        repository.delete(servicoEncontrado);
    }
}
