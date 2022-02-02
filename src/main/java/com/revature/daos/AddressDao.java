package com.revature.daos;

import com.revature.models.Address;
import java.util.List;

public interface AddressDao {

    public boolean createAddress(Address address);
    public List<Address> getAllAddresses();
    public Address getAddressById(int id);
    public boolean updateAddress(Address address);
    public boolean deleteAddress(int id);



}
