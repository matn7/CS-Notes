# Mock
```java
public class SomeBusinessMockTest {
    @Test
    public void textFindTheGreatestFromAllData() {
        DataService dataServiceMock = Mockito.mock(DataService.class);
        Mockito.when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{4,6,12,98});

        SomeBusinessImpl business = new SomeBusinessImpl(dataServiceMock);
        int result = business.findTheGreatestFromAllData();
        Assert.assertEquals(98, result);
    }
}
```

## With Annotations
```java
@RunWith(MockitoJUnitRunner.class)
public class SomeBusinessMockAnnotationsTest {

    @Mock
    DataService dataServiceMock;

    @InjectMocks
    SomeBusinessImpl businessImpl;

    @Test
    public void textFindTheGreatestFromAllData() {
        Mockito.when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{4,6,12,98});

        int result = businessImpl.findTheGreatestFromAllData();
        Assert.assertEquals(98, result);
    }
}
```