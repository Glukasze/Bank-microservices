package com.glukasze.accounts.service;

import com.glukasze.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * Creates a new account for the given customer.
     *
     * @param customerDto the customer data transfer object containing customer details
     */
    void createAccount(CustomerDto customerDto);

    /**
     * Fetches the account details for a customer based on their mobile number.
     *
     * @param mobileNumber the mobile number of the customer
     * @return CustomerDto containing the account details
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     *
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    boolean deleteAccount(String mobileNumber);

}
