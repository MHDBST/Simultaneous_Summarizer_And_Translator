# Simultaneous_Summarizer_And_Translator

This is a B.Sc. thesis which is 'Design and Implementation of an Online Simultanous Summarizer and Translator.'

The overal system is in this way: I have some English texts which need to be summarize and translate. First I summarize these texts using my summarizer and each sentences of each text, gains its importance. Then the translator translates my English sentences to Persian language. After translating, each sentence gains a point which shows how fluent it has been translated. These two scores are weighted and all sentences of a text are sorted base on their total point. According to the number of sentences I want to extract for each text, the most important sentences are shown to the user.

This project contains two fundamental containor: The most important part is the summarizer. For the summarizer I use different papers and thesis. This is a one document summarizer which means only use features from one document to summarize the text. The method of summarizing is extractive. It means some of the most important sentences from the source text is chosen for summarizing. To summarize a text, I use some entity based features and some surface based features. The overal method is that, I define some features and then because all the features do not have same importance, each one should have its weight. To find out the weight I use Genetic Algorithm. To train the model I use Pasokh Corpora (Moghaddas, Behdad Behmadi, et al. "Pasokh: A standard corpus for the evaluation of Persian text summarizers." Computer and Knowledge Engineering (ICCKE), 2013 3th International eConference on. IEEE, 2013.) I use 11 different features. For 10 of them I use this thesis: (M. Berker“ .USING GENETIC ALGORITHMS WITH LEXICAL CHAINS FOR AUTOMATIC TEXT SUMMARIZATION ”.Bogazici University, 2011) and for the 11th one I use a page rank graph (R. Mihalcea و P. Tarau“ .TextRank: Bringing order into texts ”.in Association for Computational Linguistics .2004)

First is the translator. This system is a subsystem for my whole and The translator is by connecting to Targoman site API. (www.targoman.com)

For more information contact the author.
