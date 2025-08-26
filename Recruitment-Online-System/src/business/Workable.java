/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package business;

/**
 *
 * @author zzzdi
 */
public interface Workable<T> {

    public void addNew(T t);

    public void update(T t);

    public T searchById(String code);

    public void delete(T t);
}

