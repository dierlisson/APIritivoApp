# APIritivo 🍽️

![Kotlin](https://img.shields.io/badge/Kotlin-B125EA?style=for-the-badge&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack_Compose-4285F4?style=for-the-badge&logo=android&logoColor=white)

APIritivo é um aplicativo Android nativo desenvolvido para explorar e visualizar receitas culinárias. O projeto foi construído com as tecnologias e arquiteturas mais modernas recomendadas pelo Google, servindo como uma demonstração prática de desenvolvimento escalável, reativo e testável.

## 🚀 Funcionalidade

* **Catálogo de Receitas:** Listagem fluida de pratos consumidos diretamente de uma API pública.
* **Detalhes da Receita:** Visualização aprofundada contendo a imagem em alta qualidade e as instruções de preparo de cada prato.
* **Tratamento de Estado:** Feedback visual completo para o usuário, incluindo estados de carregamento (Loading) e tratamento amigável de erros de rede com opção de tentar novamente (Retry).
* **Navegação Declarativa:** Transições de tela seguras utilizando o Jetpack Compose Navigation.

## 🛠️ Stack Tecnológica e Arquitetura

O aplicativo segue estritamente a arquitetura **MVVM (Model-View-ViewModel)**, garantindo a separação de responsabilidades e facilitando a cobertura de testes.

* **Linguagem:** [Kotlin](https://kotlinlang.org/)
* **Interface (UI):** [Jetpack Compose](https://developer.android.com/jetpack/compose) (Componentes declarativos, `LazyColumn`, `Scaffold`)
* **Navegação:** [Navigation Compose](https://developer.android.com/jetpack/compose/navigation)
* **Injeção de Dependência:** [Dagger Hilt](https://dagger.dev/hilt/)
* **Requisições de Rede:** [Retrofit](https://square.github.io/retrofit/) + [Gson](https://github.com/google/gson)
* **Carregamento de Imagens:** [Coil](https://coil-kt.github.io/coil/compose/) (Assíncrono e otimizado para Compose)
* **Programação Assíncrona & Reatividade:** [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)
* **Testes Unitários:** [JUnit4](https://junit.org/junit4/), [MockK](https://mockk.io/) e `kotlinx-coroutines-test`

## 💡 Sobre a API Utilizada (TheMealDB)

Este projeto consome os dados da **TheMealDB**. A escolha por esta API pública ocorreu estrategicamente para facilitar a execução, avaliação e os testes do aplicativo por qualquer pessoa que clone o repositório.

Diferente de alternativas que exigem autenticação rigorosa, a TheMealDB é 100% gratuita, não impõe limites estritos de requisições e **não exige a configuração prévia de uma chave de autenticação (API Key)**. Isso garante que o projeto seja totalmente *plug and play* — basta clonar, compilar e rodar imediatamente, sem burocracias.

## 📂 Estrutura do Projeto

A base de código está organizada por domínios de responsabilidade (`feature-by-layer` ajustado):

* `data/`: Modelos de dados (`Meal`, `MealResponse`) e interfaces de comunicação (`MealApi`).
* `di/`: Módulos de injeção de dependência do Hilt (`NetworkModule`).
* `ui/`:
    * `screens/`: Telas construídas com Jetpack Compose (`RecipeListScreen`, `RecipeDetailScreen`).
    * `viewmodel/`: Lógica de apresentação e gerenciamento de estado (`RecipeViewModel`, `RecipeUiState`).


## 💻 Como executar o projeto

1. Faça o clone deste repositório:
   ```bash
   git clone https://github.com/dierlisson/APIritivoApp.git
    ``` 
2. Abra o projeto no Android Studio (versão Iguana ou superior recomendada).

3. Aguarde o Gradle sincronizar todas as dependências.

4. Clique em Run 'app' (Shift + F10) para rodar no emulador ou dispositivo físico.

## 🧪 Testes

O projeto conta com testes unitários focados na camada de apresentação (`ViewModel`), garantindo que a lógica de busca de receitas e as emissões de estado (`Loading`, `Success`, `Error`) funcionem perfeitamente.

Para rodar os testes:
1. Abra o projeto no Android Studio.
2. Navegue até a pasta `app/src/test/java/.../ui/viewmodel/`.
3. Clique com o botão direito no arquivo `RecipeViewModelTest.kt` e selecione **Run 'RecipeViewModelTest'**.

## 📹 Demonstração do App

Confira o aplicativo em funcionamento, demonstrando a sincronização com a API, adição e remoção de tarefas e a funcionalidade "Pull to Refresh".

<video src="https://github.com/user-attachments/assets/8d186fb0-f384-451f-b41d-2cc316616ca7" controls="controls" style="max-width: 100%; height: auto;">
  Seu navegador não suporta a tag de vídeo.
</video>



## 👤 Autor

    Desenvolvido por **Dierlisson Justiniano** como parte de um desafio prático de desenvolvimento Android.