function imprimirOla() {
        console.log("Olá");
    }
    imprimirOla();

    function imprimirSoma(var1, var2) {
        console.log(var1 + var2);
    }
    imprimirSoma(1,1);
    
    // Funções podem retornar valores
    // Retornar valores significa que a função pode devolver um valor para quem a chamou
    
    function retornaASoma(var1, var2) {
        return (var1 + var2)
    }

    let soma = retornaASoma(1, 1);
    console.log(soma);