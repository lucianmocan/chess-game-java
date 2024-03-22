# Chess game with Java-FX
This is a Chess game I build in Java with Java-FX during my 2nd year of Bachelor in Computer Science at the University of Strasbourg.

The rest of the document is the content of the report I wrote for the project. (*only in French*)

## Description
Pour l’application, d’après certaines suggestions, il était nécessaire de créer un Launcher, une classe qui va appeler le main de la classe `ChessApplication` lors de l’exécution du programme. 
Ensuite, pour gérer les deux contrôleurs de vue : `StartMenuController` et `ChessController`, j’ai défini une classe `GameManager` qui a pour but de démarrer un certain Controller en dépendance de la situation. 

<img width="173" alt="image" src="https://github.com/lucianmocan/chess-game-java/assets/62904583/de20f495-4616-4e71-ba52-c31939555eda">
<img width="271" alt="image" src="https://github.com/lucianmocan/chess-game-java/assets/62904583/2282e4de-77be-4443-af31-2f3451cd217e">
<img width="202" alt="image" src="https://github.com/lucianmocan/chess-game-java/assets/62904583/ecdc8dfa-f63d-461d-bc0c-c5f82cfe750d">



#### Fonctionnement `StartMenuController` - `ChessController`
`StartMenuController` transmets les données, les paramétrages (noms, mode de jeu) saisis par l’utilisateur, à `ChessController`.
`ChessController` est la classe qui fait fonctionner l’échiquier. Elle contient une liste de `ChessTile`, ce sont les carrés. 
Un `ChessTile` contient le type de pièce qui est positionné dessus et la couleur du carré, ainsi que sa position dans la liste le contenant. 
L’idée était de pouvoir communiquer des informations facilement, et pour déplacer une pièce il suffit d’échanger les pièces de deux `ChessTile` ou pour capturer il suffit d’échanger et mettre à `null` l’ancien emplacement.

<img width="176" alt="image" src="https://github.com/lucianmocan/chess-game-java/assets/62904583/b4a43b33-fb09-4b87-9ebe-f356b4a663a2">

#### Classe `Piece` et les pièces du jeu classique et magique
Pour les pièces, j’ai défini une classe `Piece`, qui contient l’image et la couleur d’une pièce. 
Après, de `Piece` héritent, `ShortSpecificMoves`, une classe qui traite les pièces comme le `Roi` et le `Chevalier` ; `Pawn` qui représente le pion et `LongMoves`, une classe qui traite les pièces comme la `Reine`, le `Fou`, la `Tour`. 

![Pawn](https://github.com/lucianmocan/chess-game-java/assets/62904583/b9d3b7c5-77f5-48dd-afe3-750e6bbc94e3)

J’ai défini les pièces standard : 

<img width="452" alt="image" src="https://github.com/lucianmocan/chess-game-java/assets/62904583/023082a7-75aa-48c9-a743-44f75a214f21">

Et les pièces magiques : 

![MagicHorseKing](https://github.com/lucianmocan/chess-game-java/assets/62904583/23a91754-f073-43e2-b29d-146b8ba9ca65)
 
- MagicBishop est un Fou, mais qui se limite à 3 cases.
- MagicSquare est un Roi, mais qui fait un mouvement original :

<img width="273" alt="image" src="https://github.com/lucianmocan/chess-game-java/assets/62904583/7727f5f8-7009-4a31-9726-ac9567812deb">


- MagicHorseKing est le regroupement des mouvements d’un Chevalier et un Roi
- Magic Pawn est un pion qui peut capturer dans la même direction dans laquelle il marche.

#### Calcul des mouvements possibles pour une pièce
Pour calculer les mouvements, j’utilise des coordonnées dans un tableau. 
Une méthode donne une liste des tous les mouvements possibles, inclus ceux qui sortent de l’échiquier. 
Ensuite, une autre fait le tri, c’est-à-dire garde que les bons mouvements.

#### Calcul du 'check' et 'check-mate'
Pour vérifier s’il y a un ‘check’, une méthode vérifie si le Roi se trouve dans la liste des mouvements possible d’une autre pièce de la couleur de l’équipe adverse. 
Pour vérifier un ‘check-mate’, je vérifie si tous les mouvements possibles d’un Roi vont sur une position en ‘check’ et si en plus il se trouve en ‘check’ dans la position courante alors il y a un ‘check-mate’.
