/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial3;

/**
 *
 * @author angel
 */
public abstract class Avn {
    //atributos
	protected Ala tipAla;
	//constructor
	public Avn(){ tipAla = new MutAla(); }
	//metodos
	public abstract void dspl();
	public void setTipAla( Ala tipAla ){ this.tipAla = tipAla; }
	public void dis(){ tipAla.dis(); }

}
