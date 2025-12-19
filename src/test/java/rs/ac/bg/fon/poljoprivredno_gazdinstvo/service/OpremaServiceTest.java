package rs.ac.bg.fon.poljoprivredno_gazdinstvo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import rs.ac.bg.fon.poljoprivredno_gazdinstvo.dto.impl.OpremaDto;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.entity.impl.Oprema;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.mapper.impl.OpremaMapper;
import rs.ac.bg.fon.poljoprivredno_gazdinstvo.repository.OpremaRepository;

@ExtendWith(MockitoExtension.class)
class OpremaServiceTest {

    @Mock
    private OpremaRepository opremaRepository;

    @Mock
    private OpremaMapper opremaMapper;

    @InjectMocks
    private OpremaService opremaService;

    @Test
    void testFindAllOpreme() {
        Oprema e1 = new Oprema(1L);
        Oprema e2 = new Oprema(2L);

        OpremaDto d1 = new OpremaDto(); d1.setOpremaID(1L);
        OpremaDto d2 = new OpremaDto(); d2.setOpremaID(2L);

        when(opremaRepository.findAll()).thenReturn(List.of(e1, e2));
        when(opremaMapper.toDto(e1)).thenReturn(d1);
        when(opremaMapper.toDto(e2)).thenReturn(d2);

        List<OpremaDto> result = opremaService.findAll();

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getOpremaID());
        assertEquals(2L, result.get(1).getOpremaID());

        verify(opremaRepository).findAll();
        verify(opremaMapper).toDto(e1);
        verify(opremaMapper).toDto(e2);
    }

    @Test
    void testFindByIdOpremu() {
        Long id = 1L;

        Oprema entity = new Oprema(id);
        OpremaDto dto = new OpremaDto(); dto.setOpremaID(id);

        when(opremaRepository.findById(id)).thenReturn(Optional.of(entity));
        when(opremaMapper.toDto(entity)).thenReturn(dto);

        OpremaDto result = opremaService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getOpremaID());

        verify(opremaRepository).findById(id);
        verify(opremaMapper).toDto(entity);
    }

    @Test
    void testFindByIdNotFoundOpremu() {
        Long id = 999L;

        when(opremaRepository.findById(id)).thenReturn(Optional.empty());

        OpremaDto result = opremaService.findById(id);

        assertNull(result);

        verify(opremaRepository).findById(id);
        verifyNoInteractions(opremaMapper);
    }
}
