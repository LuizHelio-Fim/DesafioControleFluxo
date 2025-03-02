package src;
import java.util.Scanner;

public class Contador {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o primeiro parâmetro");
		int parametroUm = sc.nextInt();
		System.out.println("Digite o segundo parâmetro");
		int parametroDois = sc.nextInt();
		
		try {
			contar(parametroUm, parametroDois);
		
		}catch (ParametrosInvalidosException e) {
			System.out.println("Erro:" + e.getMessage());
		}catch (Exception e) {
            System.out.println("Entrada Inválida! Certifique-se de inserir números inteiros.");
        }finally {
            sc.close();
        }
	}
	static void contar(int parametroUm, int parametroDois ) throws ParametrosInvalidosException {
		if (parametroUm > parametroDois) {
            throw new ParametrosInvalidosException("O segundo parâmetro deve ser maior que o primeiro");
        }
		
		int contagem = parametroDois - parametroUm;
		for (int i = 0; i <= contagem; i++) {
            System.out.println("Imprimindo o numero: " + i);
        }
	}
}