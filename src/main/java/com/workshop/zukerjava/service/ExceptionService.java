package com.workshop.zukerjava.service;

import org.springframework.stereotype.Service;

@Service
public class ExceptionService {

//    @Autowired
//    private ExceptionRepository exceptionRepository;
//
//    @Autowired
//    CacheManager cacheManager;
//
//    @Cacheable(cacheNames = "exceptionCache", key = "#result.forEach()}")
//    public List<Exceptions> getAllExceptions() {
//        List<Exceptions> exceptions = exceptionRepository.findAll();
//        //cacheAll(exceptions);
//        return exceptions;
//    }
//
//
//    public void cacheAll(List<Exceptions> exceptions) {
//        for (Exceptions exception: exceptions) {
//            cacheManager.getCache("exceptionCache").putIfAbsent(exception.getId(), exception);
//        }
//    }
//
//
//    @Cacheable(value = "exceptionCache")
//    public Exceptions getExceptionById(Long exceptionId) {
//        Exceptions exception =  exceptionRepository.findById(exceptionId).get();
//        return exception;
//    }
}
