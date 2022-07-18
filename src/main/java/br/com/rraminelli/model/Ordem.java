package br.com.rraminelli.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Ordem {

    private Long id;
    private List<Item> items;

}
