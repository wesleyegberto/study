/**
 * Copyright (c) 2010-2013, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 17/02/2013
 * 
 */
package anotacao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

public class MyAnnotation {
	public static void main(String[] args) {
		Pessoa p = new Pessoa();
		Field[] fields = p.getClass().getFields();
		
		System.out.println("Starts\n");
		System.out.println("How many itens: " + fields.length);
		for(Field f : fields) {
			System.out.print("Name: " + f.getName());
			if(f.isAnnotationPresent(ShowMessage.class))
				System.out.println(" has annotation ShowMessage with value " + f.getAnnotation(ShowMessage.class));
			else
				System.out.println(" has not annotation");
		}
		System.out.println("\nEnds");
	}
	
}

@ShowMessage(atributo = "pessoa")
class Pessoa {
	@ShowMessage(atributo = "nome")
	public String nome;

	@ShowMessage()
	public String cpf;

	public String endereco;
}

@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface ShowMessage {
	String atributo() default "<default value>";
}
