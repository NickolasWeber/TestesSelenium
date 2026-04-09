import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveProduct_ShouldSaveProduct_WhenProductIsValid() {
        Product product = new Product(1, "Product Name", 100.0);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product savedProduct = productService.saveProduct(product);

        verify(productRepository, times(1)).save(product);
        assertEquals(product, savedProduct);
    }

    @Test
    void saveProduct_ShouldThrowException_WhenProductIsInvalid() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productService.saveProduct(new Product(1, "", -10.0));
        });

        String expectedMessage = "Product is invalid";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}