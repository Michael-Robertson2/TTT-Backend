package com.revature.ecommerce.entities.enums;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

public enum ItemType {
    Clothing,
    Vehicles,
    Electronics
}