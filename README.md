# jogo_da_velha_java_rmi
Jogo da velha utilizando arquitetura cliente-servidor e API Java RMI. 

# Jogo da Velha
Trabalho apresentado ao curso de Ciência da Computação, da Universidade Federal de São João del Rei, como requisito parcial para obtenção da nota final da disciplina Sistemas Distribuídos.

Este projeto consiste na implementação de um jogo da velha, com interface gráfica e arquitetura clinte-servidor. O jogo é um sistema distribuído e para tal, utiliza a API JAVA RMI para possibilitar a comunicação entre as partes cliente e servidor por meio de Invocação Remota de Métodos.

## Projeto 


## Execução
Para executar o jogo da velha é necessário executar quatro janelas do seu terminal de usuário:

1 - Na primeira janela você vai acessar a pasta contendo os arquivos do jogo e executar os seguintes comandos:

    javac *.java
    
    rmic JogoDaVelhaServidorImplementacao
    
    rmiregistry
    
2 - Na segunda janela de terminal vocÊ vai acessar a mesma pasta e executar o seguinte comando:

    java JogoDaVelhaServidor
        
3 - Na terceira janela do terminal você vai acessar novamente a mesma pasta e executar o seguinte comando:
    
    java JogoDaVelhaCliente
        
3 - E por fim, na quarta e ultima janela do terminal você vai acessar a mesma pasta e executar o seguinte comando:

    java JogoDaVelhaCliente  

Assim, será possivel visualizar duas janelas do java (Swing), que são a interface gráfica do jogo da velha que foi implementado.      

## Saída
Interface gráfica do jogo.

### Desenvolvido por:
Igor Rodrigues 

Ricardo de Souza Monteiro   


