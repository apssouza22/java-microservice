package com.apssouza.services;

import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

//    @Autowired
//    private AccountRepository accountRepository;
//
//    @Override
//    public Optional<Account> findById(long id) {
//        return Optional.ofNullable(this.accountRepository.findOne(id));
//    }
//
//    @Override
//    public List<Account> all() {
//        return this.accountRepository.findAll();
//    }
//
//    @Override
//    public Account save(Account user) {
//        return this.accountRepository.save(user);
//    }
//
//    @Override
//    public Account update(Long id, Account user) throws DataNotFoundException{
//        return this.findById(id)
//                .map(u -> {
//                    u.setEmail(user.getEmail());
//                    u.setName(user.getName());
//                    u.setAuthId(user.getAuthId());
//                    return save(u);
//                }).orElseThrow(() -> new DataNotFoundException("User not found"));
//    }
//    
//    @Override
//    public Optional<Account> findByEmail(String email){
//        return accountRepository.findByEmail(email);
//    }
}
