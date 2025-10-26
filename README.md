# Game Description
Rock paper scissors that consists of two different game modes; the first mode is random mode.
In this game mode, the user plays against the computere three times per round, by selecting either rock, paper or scissors.
The second mode is AI mode. The user plays against an enhanced computer with AI that plays optimally agains the user. The description of the algorithm is found further in this readme file.

## Students
Students: Xiaofu Tan, Sol Moon

### How to play the game.
1. Git and clone this game from the repository or download the zip files.
2. Open the 'tue-q-programming.....' directory in visual studio code.
3. Open 'view' folder.
4. Open 'GameView.java' folder.
5. Click on the 'run java' button at top right in the editor. The button is in shape of a triangle.

#### Topic of choice 1. Github
The first topic that we choose is Github. The reason is because Github is one of the most commonly used version control platforms in the word; from hobby coders to open-source projects or enterprise level products, every developer has to know how to work around it. 

From our research, we have learned that the system behind Github is very complex that involves many different layers of features and functionalities. For this assignment, we decided to stick to the basics for the sake of simplicity of our project and maintain an appropriate level of learning experience.

Working in local branch
We decided to work mainly on the ‘main’ local branch without branching. The reason is because, the ‘main’ branch is the default branch in most of the cases.

Basic commends: add, commit, and push
Once we have initialized and created the main branch, the next step was to create code. Every time we created a new code, there was a specific sequence of commands that we had to execute in the terminal: add, commit, and push. 

Appropriate commit messages
One of the most important aspects of developing the game was good communication among the team members. Especially when it comes to coding, it was important to let each other know what each commit and push was about. Therefore, we created a specific set of rules to guide ourselves.

The message must be sufficiently descriptive about the change without overboarding.
Any other type of message is not allowed.

Final outcome
The final outcome of this topic is a Github repository that contains all of our source code to run the game with a commit history that reflects all changes that we have made so far. Just by looking at the commit history, you can grasp what we did in each of our development stages of the game.


##### Topic of choice 2. Algorithm
“Rock, Scissor, Paper” is a perfect example to be applied to Algorithm logic. 
Concepts shown:
Algorithm using statistics


Data-driven decision-making


Adaptive game behavior
Document the logic — show the flow or pseudocode.


“Random AI” vs “Predictive AI”  
There are 2 modes in the game – “Random mode” and “Endless mode”.

In the random mode, the AI plays against  the player by making its choices among “Rock”, “Scissor” and “Paper” purely randomly. The one who wins 2 out of 3 trials wins.

In the endless mode,the player has unlimited amounts of trials against the computer. Additionally Markov chains with ensemble AI, which has 4 levels and 9 decay, so totally 36 models are applied in this mode.

Predictive AI – Ensembler AI
Core ideas
Concept
Explanation
Markov Chain
Tracks transitions between recent moves, e.g. “after Rock → Paper, what comes next?”
Memory level (1–4)
How many past moves it uses as a “state” key. Example: if level = 2, state might be “PR” (player played Paper then Rock).
Probability matrix
Each state keeps probabilities of the next move (R/P/S). These are updated after every round.
Ensembler
Combines multiple Markov models (different memory depths or decay rates) and picks the best-performing one dynamically.
Score decay (score_mem 9)
Models that predict well keep high scores; bad models’ scores decay exponentially.
Prediction
The ensemble chooses the move with the highest weighted probability.
Output
The AI then plays the winning move against that prediction.


Steps

Every round, it remembers what the player and AI chose.
Each MarkovAI (36 totally) model updates its transition table — e.g. “After Rock → Paper, what tends to come next?”
Each model predicts the next player move.
EnsembleAI collects all predictions and weights them (models that performed better in the past have higher influence).
It picks the counter move to the most likely predicted player choice.


###### References
Atlassian. (2025). Git commands overview. In Atlassian Bitbucket Cloud documentation. Atlassian. https://www.atlassian.com/git/tutorials


How to win over 70% matches in Rock Paper Scissors – An elementary AI approach to a populargame.https://medium.com/data-science/how-to-win-over-70-matches-in-rock-paper-scissors-3e17e67e0dab



