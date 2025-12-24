package com.albin.parkgest.service;

import com.albin.parkgest.repository.UserRepository;
import com.albin.parkgest.repository.VagasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VagasService {

    @Autowired
    private VagasRepository vagasRepository;

    @Autowired
    private UserRepository userRepository;
}
