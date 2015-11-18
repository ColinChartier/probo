#probo#
##What is probo?##
Probo is a validity checker for a certain restrictive kinds of proofs.

It uses antlr to parse the proof, and then uses a list of axioms to determine if each transformation is valid.

##What is a proof?##
Proofs are mathematical structures that prove beyond a doubt that something is true.

Let's say we want to solve this phrase: "all numbers have a number that is greater than them"
We need to translate that into something that is more objective, since english has lots of ambiguity.

In our example, we would translate it to this predicate expression:

"?x??:?y??:y>x"

But how would we prove that?
###Example proof###

Let x?? //We don't get to say what the variable is, only that it exists.
[we could prove that, say, 5 has a number greater than it, but that is far from proving ALL numbers have a number greater than them.]

Let y=x+1?? //We get to choose y, because we are saying "there exists at least one", so if we prove one exists, we are validating our claim.

x=x //This would be an axiom, namely, that any value is equal to itself

x+1>x //This is another axiom, namely that adding a positive number to one side of an equality will make that side bigger.

y>x [Since x+1=y] //We are giving justification here, but it is not necessary in such a simple example.

QED //Fancy way of saying the proof is complete.


Therefore, in our proof, we have shown from a few basic axioms that something very hard to intuitively think through is true.