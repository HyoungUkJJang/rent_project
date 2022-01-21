package com.rent.rentshop.product.service;

import com.rent.rentshop.product.domain.HashTag;
import com.rent.rentshop.product.repository.HashTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HashTagServiceImpl implements HashTagService{

    private final HashTagRepository hashTagRepository;

    @Override
    @Transactional
    public HashTag save(HashTag hashTag) {
        return hashTagRepository.save(hashTag);
    }

}
