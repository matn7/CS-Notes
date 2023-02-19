- To add values to a collection within a JPA Entity, always use the getter method first and all the objects afterwards
```java
basicUser.getUserRoles().addAll(userRoles);
```

- In spring use private final properties instead of @Autowired

```java
private final UserRepository userRepository;

public SocialService(UserRepository userRepository) {
    this.userRepository = userRepository;
}
```

## Interface Naming Conventions

- Interface should be a good object name
    - Example Java List interface
        - Implementations - ArrayList, LinkedList, CheckedList
- Don't start with "|"
    - No IList

- When just one implementation - generally accepted to use <Interface Name> + Impl
- When more than one, name should indicate difference of Implementation

## Maven

```bash
mvn release:prepare
```

## how to learn

- Learning a large amount of information can be challenging, but there are several strategies that can help you absorb 
and retain the information more effectively:
- Break it down: Break the information into smaller chunks and focus on one piece at a time. This will make it less 
overwhelming and easier to understand.
- Active learning: Try to engage with the information as much as possible. Instead of passively reading or listening, 
actively participate in the learning process by taking notes, summarizing, or teaching the material to someone else.
- Practice and repetition: Repetition is key for retention. Try to review the material regularly, whether it's through 
flashcards, quizzes, or practice problems.
- Use mnemonics or memory aids: Memory aids such as mnemonics or diagrams can help you associate new information 
with something you already know and make it easier to recall later.
- Use multiple learning styles: Everyone learns differently. Try to incorporate different learning styles into your 
study routine, such as visual, auditory, and kinesthetic.
- Prioritize: Not all the information will be equally important. Prioritize the most important information and focus on 
that first.
- Take breaks: Try to take regular breaks, it's easier to absorb information in shorter study sessions rather than 
longer ones.
- Sleep: Sleep is important, it's when the brain consolidates new information and makes connections between neurons.
- Seek help: Don't be afraid to ask for help or seek additional resources if you're struggling to understand the material.
- Practice with real-world examples: Try to apply what you learned to real-world examples, it will help you to understand 
the concepts better, and make them more meaningful.
- Remember, learning takes time and effort, and it's important to be patient and persistent. 
- By incorporating these strategies into your study routine, you can make the learning process more efficient and effective.