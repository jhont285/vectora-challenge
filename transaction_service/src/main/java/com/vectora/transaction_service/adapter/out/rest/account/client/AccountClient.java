package com.vectora.transaction_service.adapter.out.rest.account.client;

import com.vectora.transaction_service.adapter.out.rest.account.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "accountClient", url = "${account.client.url}")
public interface AccountClient {

    @GetMapping("accounts/{id}")
    Account getAccount(@PathVariable("id") int id);

}
