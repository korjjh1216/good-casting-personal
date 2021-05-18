package shop.goodcasting.api.user.producer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import shop.goodcasting.api.user.producer.repository.ProducerRepository;

@Log
@Service
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {
    private final ProducerRepository repo;
    private final ModelMapper modelMapper;


}
