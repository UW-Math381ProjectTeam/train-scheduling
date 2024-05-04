Project Name: Scheduling physical exercises for enhanced engagement while maintaining training outcome


Group Members
Chingya Ni, David Visna Miller, Weifan Wu, Yixuan Wang, Yusong Yan, Junbo Huang


Community Partner
Kari Payne, Certified Personal Trainer
Email: karinicolepayne@gmail.com


Goal of model
Given trainer input about their fitness level, available time, and preference of being supervised
by the trainer, design a safe, effective, and engaging, bi-weekly schedule that achieves the
client’s training goals.

Impact on the community
According to Chirstina et. al (2021), one of the strongest predictors of regular exercise is
“enjoyment”. By implementing this model, we would allow more people to exercise with more
engagement, leading to more people stick to their exercise routine. Moreover, this model can
allow anyone to plan a specific workout schedule personalized to their needs and body
conditions, which may allow the trainee to get the maximum benefit from the training. Lastly,
this model saves the time and effort of the trainers to plan a proper workout schedule for the
trainees. In general, the model can benefit both trainers and trainees, allowing the trainers to
determine the best training schedule without the trouble of deciding from so many different
variables while increasing the effectiveness of the training by setting up personal schedules and
increasing engagement.


Methodologies
Since this problem is very similar to the coloring problem in graphs, where one needs to color
the graph such that no edge is connected to two vertices that have the same color, we plan to use
a graph to solve this problem.
We will create a graph that has n * m vertices, where n is the number of days in the schedule,
which is 14, and m is the number of exercise a client should do during one single training
session. We would connect the graph by the constraints defined by “engagement”, such as no
exercise can show up more than 1 time on a single day or in i consecutive days. We will “color”
the vertices with different types of possible exercises that are available to the trainee. We can
also not color some of the vertices to represent that the client is not working our that day. The
list of available types of exercises may change due to the constraint that some needs to be
supervised by a trainer first before doing it on the client’s own. Another part where the types of
exercises is constrained apart from the constraints from the graph is the part of muscles the
exercise is trying to enhance (we don’t want the client to exercise the same muscles for
consecutive days as that would be tiring and painfu, which reduces engagement). To find a valid
solution, we would need to enumerate all the possibilities to determine whether it is possible to
create such a schedule. However, that would have a bad runtime, and it would be better if one
can make the runtime be polynomial. In the current stage, the team has not figured out how to
reduce the runtime, and would carry our testing all possible cases if necessary as the types of
exercises is limited and should not be a large number.



Example

Description
Suppose we have a client who would like to have their personal schedule designed. They will
provide their body conditions, time available, and the goal they want to achieve. We would run
the algorithm above to produce a coloring of the graph that assigns different exercises to vertices.
Then, we would translate the resulting graph into an actual schedule that determines what kinds
of exercises the client should do each day in the bi-weekly schedule.


References
Platt, Geoffrey K. “The Principles of Training.” In Complete Guide to Lifting Heavy Weights.
United Kingdom: Bloomsbury Publishing, 2011.
Usage: Will be used to find different types of exercises that can be done. Also, mentions different
weight training scenarios for how long they should take a break. Can give us ideas of how long a
break for each muscle group can be.

McNamara, John M, and David J Stearne. “Flexible Nonlinear Periodization in a Beginner
College Weight Training Class.” Journal of Strength and Conditioning Research 24, no. 1
(2010): 17–22. doi:10.1519/JSC.0b013e3181bc177b.
Usage: This is another mathematical model that is representing the different scenarios we could
try and use in our model. However, they seem to be going for a quick increase in muscle mass.
Our client may not want us to do this and each person our client works with could want to do a
different type of progression.

McGarr, Cameron, and Sean Hyson. "The MF beginner's guide to weight training: if you're a
gym neophyte, our comprehensive playbook will help you transform your body in record time."
Men's Fitness, vol. 24, no. 1, Feb. 2008, pp. 107+. Gale Academic OneFile,
link.gale.com/apps/doc/A174281751/AONE?u=wash_main&sid=bookmark-AONE&xid=4fb5cb
e2. Accessed 11 Apr. 2024.
Usage: Possible lookout for a type of exercise schedule we could format ours to have a similar
output to.

Lass, Lanie. “Implementing a Weight Training Program for Beginners.” Recreational Sports
Journal 14, no. 1 (1989): 36–56. doi:10.1123/nirsa.14.1.36.
Usage: I think this will have one of the biggest impacts on deciding the course of the
mathematical modeling on the exercise part of things. Being able to have an outline of figuring
out where to put each muscle group situation was needed.

Pritchard, Tony, Kellie Penix, Gavin Colquitt, and Starla McCollum. “Effects of a Weight
Training Personalized System of Instruction Course on Fitness Levels and Knowledge.” The
Physical Educator 69, no. 4 (2012): 342-.
Usage: Another mathematical model, these will be good to see what sources they may pull from
to get numbers that show average for a general population and then add additional restraints to
ours such as disabilities. However, this model mainly focuses on showing a model being more
appropriate for self-improvement, through the client we are working with we hope to see steady
gains from their own clients.

Christina Christina Gjestvang, Frank Abrahamsen, Trine Stensrud, & Lene A. H. Haakstaal
(2021). What Makes Individuals Stick to Their Exercise Regime? A One-Year Follow-Up Study
Among Novice Exercisers in a Fitness Club Setting. National Library of Medicine.
https://www.ncbi.nlm.nih.gov/pmc/articles/PMC8194699/
