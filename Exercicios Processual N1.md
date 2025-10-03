
	### Questões sobre o Processo de Compilação em Java

1.	Qual é a principal diferença entre o processo de compilação em Java e o de linguagens C++? 
	C++ compila para código de máquina nativo e específico de plataforma, enquanto Java compila para bytecode intermediário que é executado na Java Virtual Machine (JVM), proporcionando portabilidade em vez de desempenho bruto direto
        2.Explique o que acontece em cada uma das três fases de análise do compilador **javac**: Análise Léxica, Análise Sintática e Análise Semântica.
	Análise Léxica (Scanning): O compilador lê o seu código-fonte, que é apenas um arquivo de texto, e o divide em pequenas unidades chamadas **tokens**.
	Análise Sintática (Parsing): Após a fase léxica, o compilador verifica se a sequência de tokens segue a gramática da linguagem Java. Ele constrói uma **Árvore Sintática Abstrata (AST)**, que é uma representação hierárquica da estrutura do seu código. Se houver um erro de sintaxe (por exemplo, um ponto e vírgula esquecido), o compilador não consegue construir a AST e o processo para.
	Análise Semântica: Esta é a fase mais complexa. O compilador verifica se o código faz sentido lógico. Ele checa se as variáveis foram declaradas antes de serem usadas, se os tipos de dados são compatíveis (por exemplo, você não pode atribuir uma `String` a uma variável `int`), e se os métodos estão sendo chamados corretamente. Erros como `incompatible types` (tipos incompatíveis) ou `cannot find symbol` (símbolo não encontrado) são encontrados aqui.
   
3.O que é o **bytecode** e qual é a sua principal função no processo de compilação do Java?
	O bytecode é um código intermediário criado pelo compilador Java a partir do código-fonte. Sua principal função é garantir a portabilidade do Java, permitindo que o mesmo arquivo .class (que contém o bytecode) seja executado em qualquer sistema operacional ou dispositivo que tenha uma JVM instalada. Ele é o segredo por trás da filosofia "Write Once, Run Anywhere" (Escreva uma Vez, Execute em Qualquer Lugar).
4.	Qual é o papel da **Máquina Virtual Java (JVM)** na execução de um programa Java, e por que o arquivo ".class" não é executado diretamente pelo sistema operacional?
	A Máquina Virtual Java (JVM) atua como um interpretador e compilador que traduz o bytecode Java (arquivos .class) em código de máquina nativo para o sistema operacional e hardware específicos, garantindo que o mesmo programa Java possa ser executado em qualquer plataforma. Os arquivos .class não são executados diretamente porque o bytecode é uma linguagem intermediária, não código de máquina compreensível pela CPU, e a JVM abstrai as particularidades do sistema operacional, permitindo a portabilidade "escreva uma vez, execute em qualquer lugar". 


5. O que é o compilador **JIT** e como ele melhora o desempenho dos programas em Java?
	O compilador Just-In-Time (JIT) é um componente da Java Virtual Machine (JVM) que compila o bytecode para código de máquina nativo durante a execução do programa, não antes. Ele melhora o desempenho ao compilar apenas o código mais usado e ao otimizar esse código com base no comportamento do programa em tempo real, resultando em execução mais rápida do que a simples interpretação. 
### Questões sobre Linguagens Formais em Java

6.Qual é a aplicação mais comum e direta das linguagens formais em Java, e para que ela é utilizada?
	De acordo com o texto, a aplicação mais comum e direta das linguagens formais em Java são as Expressões Regulares (Regex). Elas são utilizadas para:
•	Validação de entrada, como verificar o formato de um e-mail ou telefone.
•	Busca e substituição de padrões de texto em strings.
•	Análise de logs para extrair informações específicas.


7. No processo de compilação de um código Java, como as linguagens formais são usadas nas fases de Análise Léxica e Análise Sintática?	
	Nas fases de Análise Léxica e Análise Sintática, as linguagens formais são usadas para definir as regras gramaticais do código Java, permitindo a verificação da estrutura do programa de forma rigorosa e não ambígua. Na análise léxica, as linguagens formais definem lexemas e tokens, agrupando caracteres em unidades com significado, enquanto na análise sintática, a gramática da linguagem é usada para verificar se a sequência de tokens forma uma estrutura válida, como a construção de uma árvore sintática.
8. O que é uma **Máquina de Estado Finito (FSM)** e como ela pode ser usada em Java?
	Uma Máquina de Estado Finito (FSM) é um modelo computacional que pode reconhecer linguagens regulares. Em Java, de acordo com o texto, uma FSM pode ser usada para modelar:
•	Protocolos de comunicação.
•	Análise de eventos, onde um objeto muda de estado com base em eventos que ocorrem.
•	A implementação de analisadores léxicos.

9. Como as linguagens formais se relacionam com os schemas de validação de documentos, como os usados para **XML** e **JSON**?
As linguagens formais estão na base dos schemas de validação de documentos. Um schema (como XML Schema ou JSON Schema) nada mais é do que uma aplicação prática de uma linguagem formal, utilizada para definir a gramática e as restrições de documentos estruturados.
•	Linguagens formais: fornecem a estrutura teórica para descrever sintaxe e semântica de dados.
•	Schemas: implementam essa teoria, especificando regras como:
o	quais elementos/atributos podem aparecer,
o	quais tipos de dados são aceitos (string, número, booleano etc.),
o	cardinalidade (se um campo é obrigatório, opcional, repetível),
o	restrições de formato (ex.: regex para validar um e-mail).
Assim, os schemas garantem que documentos XML ou JSON obedeçam a um formato pré-definido e coerente, permitindo validação automática, interoperabilidade entre sistemas e redução de erros na troca de informações.


10. De acordo com o texto, qual é a principal utilidade de ferramentas como o **ANTLR** no contexto de linguagens formais em Java?
  	De acordo com o texto, a principal utilidade de ferramentas como o ANTLR no contexto de linguagens formais em Java é automatizar a criação de analisadores léxicos e sintáticos.
	Com o ANTLR, o programador pode definir a gramática de uma linguagem formal e a ferramenta gera automaticamente o código Java necessário para reconhecer e processar essa linguagem, construindo parsers que verificam se uma sequência de tokens segue as regras gramaticais estabelecidas
