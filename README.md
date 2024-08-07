# Présentation du Code

## 1. Introduction du Projet
Le projet consiste à concevoir un jeu de bataille navale où deux joueurs s'affrontent en plaçant et attaquant des navires sur une grille.

## 2. Architecture du Code

### Classes Principales :
- **BatailleNavaleApplication** : Initialise le jeu

### Les Modèles
- **BatailleNavale** : Objet qui contient la logique du jeu
- **BatailleNavaleConsole** : Gère les interactions avec la console (affichage et saisie)
- **Joueur** : Représente un joueur
- **GrilleNavire** : Représente la grille de jeu d’un joueur, elle contient les navires
- **GrilleAdversaire** : Représente la grille de l’adversaire, permet d’afficher les attaques faites par un joueur
- **Navire et ses sous-classes** (ContreTorprilleur, Croiseur, PorteAvion, SousMarin, Torpilleur) : Représentent les différents types de navires
- **GrilleManager** : Pour gérer les ressources communes aux grilles
- **Cellules** : représente la liste de cellule clonable
- **Navires** : représente la liste de navires clonables
- **Cellule** : Représentent les cellules des grilles
- **Coordonnee** : Représente les coordonnées d’une cellule, facilite la manipulation des cellules
- **Orientation** : Enumération pour les orientations des navires, utile au placement de navire
- **CelluleStatus** : Enumération pour définir l'état des cellules

### Les Interfaces
- **AffichageConsole** : Interface fonctionnelle pour l’affichage de données

### Les Exceptions
- **InvalidePositionException** : Représente une erreur de positionnement
- **NonTrouveException** : Représente une erreur de récupération

## 3. Concepts Clés de Programmation

### Héritage
- Les différentes classes de navires (ContreTorpilleur, Croiseur, etc.) héritent de la classe abstraite Navire.
- GrilleNavire et GrilleAdversaire héritent de la classe abstraite Grille.

### Polymorphisme
- Utilisation de l'interface **AffichageConsole** implémentée pour simplifier la commande d'affichage dans la console.
- Les méthodes héritées de la classe `Grille` sont propres à chaque sous-classe

### Encapsulation
- Utilisation de propriétés privées avec des getters et setters, comme dans Joueur, Navire, et Cellule.
- Chaque objet contient du code qui lui est propre.

### Singleton
- La **GrilleManager** est un singleton, car il représente un ressource partagée dans tous le programme.

### Prototype
- Les classes **Cellules** et **Navires** sont des listes clonables pour pouvoir être utilisées dans tous le programme

## 4. Fonctionnalités Principales

### Initialisation du Jeu
- `BatailleNavaleApplication.main` initialise et démarre une partie.

### Placement des Navires
- `BatailleNavale.placerNavires` : Chaque joueur place ses navires sur la grille en utilisant des méthodes de Menu.

### Attaque
- `BatailleNavale.attaquer` : Les joueurs attaquent tour à tour jusqu'à ce que tous les navires d'un joueur soient détruits.
- La méthode `BatailleNavale.SetAttaque` : c'est une méthode récursive.

### Affichage
- Les grilles sont affichées à l'utilisateur, montrant les positions des navires et les résultats des attaques.

## Conclusion
Ce projet utilise des concepts avancés de programmation orientée objet, tels que l'héritage, le polymorphisme, et l'encapsulation, pour créer un jeu de bataille navale interactif et structuré.
