package com.workintech.s18d4.controller;

import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.AccountService;
import com.workintech.s18d4.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final CustomerService customerService;

    @GetMapping
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Account find(@PathVariable Long id) {
        return accountService.find(id);
    }

    @PostMapping("/{customerId}")
    public Account save(@PathVariable("customerId") Long customerId, @RequestBody Account account) {
        Customer customer = customerService.find(customerId);
        if (customer != null) {
            account.setCustomer(customer);
            // İlişkiyi diğer taraftan da güncellemek iyi pratiktir
            customer.getAccounts().add(account);
            // Account save edilmeli, cascade ayarına göre customer save edilince account da save olabilir
            // ama burada direkt account dönmemiz beklendiği için:
            return accountService.save(account);
        }
        throw new RuntimeException("Customer not found");
    }

    @PutMapping("/{customerId}")
    public Account update(@PathVariable("customerId") Long customerId, @RequestBody Account account) {
        Customer customer = customerService.find(customerId);
        if (customer != null) {
            account.setCustomer(customer); // İlişkiyi güncelle
            return accountService.save(account);
        }
        throw new RuntimeException("Customer not found");
    }

    @DeleteMapping("/{id}")
    public Account delete(@PathVariable Long id) {
        return accountService.delete(id);
    }
}