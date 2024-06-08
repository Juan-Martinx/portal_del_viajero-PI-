package com.pdv.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.pdv.dto.AlojamientoComodidadAlojamientoDTO;
import com.pdv.dto.AlojamientoDTO;
import com.pdv.dto.GenericAPIMessageDTO;
import com.pdv.dto.ImagenAlojamientoDTO;
import com.pdv.dto.UbicacionAlojamientoDTO;
import com.pdv.dto.UsuarioDTO;
import com.pdv.dto.ValoracionAlojamientoDTO;
import com.pdv.enums.CodPerfiles;
import com.pdv.model.Alojamiento;
import com.pdv.model.AlojamientoComodidadAlojamiento;
import com.pdv.model.ImagenAlojamiento;
import com.pdv.model.UbicacionAlojamiento;
import com.pdv.repository.AlojamientoComodidadAlojamientoRepository;
import com.pdv.repository.AlojamientoRepository;
import com.pdv.repository.ComodidadAlojamientoRepository;
import com.pdv.repository.ImagenAlojamientoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlojamientoService {

	private final AlojamientoRepository alojamientoRepository;
	private final UsuarioService usuarioService;
	private final ComodidadAlojamientoRepository comodidadAlojamientoRepository;
	private final AlojamientoComodidadAlojamientoRepository alojamientoComodidadAlojamientoRepository;
	private final AlojamientoComodidadAlojamientoService alojamientoComodidadAlojamientoService;
	private final ValoracionAlojamientoService valoracionAlojamientoService;
	private final ImagenAlojamientoService imagenAlojamientoService;
	private final ImagenAlojamientoRepository imagenAlojamientoRepository;
	
	/**
	 * Método que obtiene todos los alojamientos pertenecientes al usuario
	 * logueado dentro de la aplicación.
	 * @param autenticacion
	 * @return
	 */
	public List<AlojamientoDTO> buscarAlojamientoUsuario(Authentication autenticacion) {
		var usuario = this.usuarioService.obtenerUsuarioApp(autenticacion);
		var alojamientosList = this.alojamientoRepository.findByIdUsuarioId(usuario.getId());
		var dtoList = new ArrayList<AlojamientoDTO>();
		if (alojamientosList.isPresent() && !alojamientosList.get().isEmpty()) {
			alojamientosList.get().forEach(jpa -> {
				dtoList.add(this.toDto(jpa));
			});
		}
		return dtoList;
	}

	/**
	 * Método de busqueda general de los alojamientos. Es el que se usa en la página de 
	 * inicio para encontrar los distintos alojamientos, usa los siguientes filtros:
	 * @param provincia
	 * @param idComodidades
	 * @param numPrecioNocheMin
	 * @param numPrecioNocheMax
	 * @param fechaLlegada
	 * @param fechaSalida
	 * @param page
	 * @return
	 */
	public List<AlojamientoDTO> buscarAlojamientoWithFilters(String provincia, List<Long> idComodidades,
			Double numPrecioNocheMin, Double numPrecioNocheMax, LocalDate fechaLlegada, LocalDate fechaSalida,
			Pageable page) {

		// INICIALIZAMOS LOS DATOS EN CASO DE SER NULOS
		fechaLlegada = (fechaLlegada != null) ? fechaLlegada : LocalDate.now();
		fechaSalida = (fechaSalida != null) ? fechaSalida : LocalDate.now();
		idComodidades = idComodidades != null ? idComodidades : new ArrayList<>(Collections.singletonList(-1L));

		var idAlojamientosList = this.alojamientoRepository.findWithFilters(provincia, numPrecioNocheMin,
				numPrecioNocheMax, fechaLlegada, fechaSalida, idComodidades, idComodidades.size(), page);
		var jpaList = this.alojamientoRepository.findAllById(idAlojamientosList);
		var dtoList = new ArrayList<AlojamientoDTO>();
		jpaList.forEach(jpa -> {
			dtoList.add(this.toDto(jpa));
		});
		return dtoList;
	}
	
	/**
	 * Método de búsqueda de un alojamiento según su id
	 * @param id
	 * @return
	 */
	public AlojamientoDTO buscarAlojamientoById(Long id) {
		var jpa = this.alojamientoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Alojamiento no encontrado"));
		return this.toDto(jpa);
	}
	
	/**
	 * Busqueda de un alojamiento para su gestión, en caso de estar
	 * intentando encontrar el alojamiento de otro usuario que no sea
	 * el logueado para gestionarlo, este método no retornará nada, a menos
	 * que el usuario logueado tenga el perfil de administrador.
	 * @param id
	 * @param autenticacion
	 * @return
	 */
	public AlojamientoDTO buscarAlojamientoByIdForGestion(Long id, Authentication autenticacion) {
		var jpa = this.alojamientoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Alojamiento no encontrado"));
		boolean isAdmin = autenticacion.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.anyMatch(authority -> CodPerfiles.PERFIL_ADMIN.name().equals(authority));
		if (jpa.getIdUsuario().getId() == usuarioService.obtenerUsuarioApp(autenticacion).getId() || isAdmin) {
			return this.toDto(jpa);
		}
		return null;
	}
	
	/**
	 * Busqueda de un alojamiento por el username
	 * del usuario vinculado a él.
	 * @param username
	 * @return
	 */
	public List<AlojamientoDTO> buscarAlojamientoByUsername(String username) {
		var jpaList = this.alojamientoRepository.findByIdUsuarioUsername(username);
		var dtoList = new ArrayList<AlojamientoDTO>();
		if (jpaList.isPresent() && !jpaList.get().isEmpty()) {
			jpaList.get().forEach(jpa -> {
				dtoList.add(this.toDto(jpa));
			});
		}
		return dtoList;
	}
	
	/**
	 * Método que sirve para crear un alojamiento.
	 * @param dto
	 * @param autenticacion
	 * @return
	 */
	@Transactional
	public GenericAPIMessageDTO aniadirAlojamiento(AlojamientoDTO dto, Authentication autenticacion) {

		// Crear el objeto Alojamiento
		var jpa = Alojamiento.builder().idUsuario(usuarioService.obtenerUsuarioApp(autenticacion))
				.txtNombre(dto.getTxtNombre()).txtDescripcion(dto.getTxtDescripcion()).numPlazaMin(dto.getNumPlazaMin())
				.numPlazaMax(dto.getNumPlazaMax()).numPrecioNoche(dto.getNumPrecioNoche()).build();

		// Crear UbicacionAlojamiento
		var ubicacionJpa = UbicacionAlojamiento.builder().idAlojamiento(jpa)
				.codigoPostal(dto.getIdUbicacion().getCodigoPostal())
				.lineaDireccion(dto.getIdUbicacion().getLineaDireccion()).provincia(dto.getIdUbicacion().getProvincia())
				.build();

		// Crear AlojamientoComodidadAlojamiento
		var alojamientoComodidadesSet = new HashSet<AlojamientoComodidadAlojamiento>();
		dto.getIdComodidades().forEach(comodidadId -> {
		    var jpaComodidad = this.comodidadAlojamientoRepository.findById(comodidadId)
		            .orElseThrow(() -> new RuntimeException("Comodidad no encontrada"));
		    var alojamientoComodidadAlojamiento = AlojamientoComodidadAlojamiento.builder()
		            .idAlojamiento(jpa)
		            .idComodidadAlojamiento(jpaComodidad)
		            .build();
		    alojamientoComodidadesSet.add(alojamientoComodidadAlojamiento);
		});

		// Crear imagenes de alojamiento
		var imagenesAlojamientosSet = new HashSet<ImagenAlojamiento>();
		//Controla si se ha seleccionado una imagen principal
		AtomicReference<Boolean> isNumOrdenSelected = new AtomicReference<>(false);

		dto.getIdImagenesAlojamiento().forEach(imagenDto -> {
			if (imagenDto != null && imagenDto.getNumOrden() != null & imagenDto.getUrlDatosImagen() != null) {
				var jpaImagen = ImagenAlojamiento.builder().idAlojamiento(jpa)
						.urlDatosImagen(imagenDto.getUrlDatosImagen()).numOrden(imagenDto.getNumOrden()).build();
				isNumOrdenSelected.set(isNumOrdenSelected.get() || jpaImagen.getNumOrden() == 0 ? true : false);
				imagenesAlojamientosSet.add(jpaImagen);
			}
		});
		
		if(!isNumOrdenSelected.get()) {
			return GenericAPIMessageDTO.builder().estado(HttpStatus.OK).mensaje("¡No puedes crear un alojamiento sin asignarle la imágen principal!")
					.fechaYHora(LocalDateTime.now()).build();
		}
		
		jpa.setIdUbicacion(ubicacionJpa);
		jpa.setIdAlojamientoComodidades(alojamientoComodidadesSet);
		jpa.setIdImagenesAlojamiento(imagenesAlojamientosSet);

		this.alojamientoRepository.save(jpa);

		return GenericAPIMessageDTO.builder().estado(HttpStatus.OK).mensaje("¡Alojamiento creado con éxito!")
				.fechaYHora(LocalDateTime.now()).build();
	}

	/**
	 * Método que sirve para modificar un alojamiento existente
	 * en caso de no pertenecerte, o en caso de que no tengas el 
	 * perfil de administrador, no podrás hacerlo.
	 * @param dto
	 * @param autenticacion
	 * @return
	 */
	@Transactional
	public GenericAPIMessageDTO modificarAlojamiento(AlojamientoDTO dto, Authentication autenticacion) {
		var jpa = this.alojamientoRepository.findById(dto.getId())
				.orElseThrow(() -> new RuntimeException("Alojamiento no encontrado"));

		var usuarioAutenticado = usuarioService.obtenerUsuarioApp(autenticacion);

		boolean isAdmin = autenticacion.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.anyMatch(authority -> CodPerfiles.PERFIL_ADMIN.name().equals(authority));

		if (usuarioAutenticado.getId() != jpa.getIdUsuario().getId() && !isAdmin) {
			return GenericAPIMessageDTO.builder().estado(HttpStatus.OK)
					.mensaje("¡No debes modificar el alojamiento de otro usuario!").fechaYHora(LocalDateTime.now())
					.build();
		}

		// MODIFICAR ATRIBUTOS DEL JPA
		jpa.setTxtNombre(dto.getTxtNombre());
		jpa.setTxtDescripcion(dto.getTxtDescripcion());
		jpa.setNumPlazaMin(dto.getNumPlazaMin());
		jpa.setNumPlazaMax(dto.getNumPlazaMax());
		jpa.setNumPrecioNoche(dto.getNumPrecioNoche());

		// MODIFICAR LA UBICACIÓN
		jpa.getIdUbicacion().setCodigoPostal(dto.getIdUbicacion().getCodigoPostal());
		jpa.getIdUbicacion().setLineaDireccion(dto.getIdUbicacion().getLineaDireccion());
		jpa.getIdUbicacion().setProvincia(dto.getIdUbicacion().getProvincia());

		// MODIFICAR ALOJAMIENTOS COMODIDADES
		var alojamientoComodidadesSet = jpa.getIdAlojamientoComodidades();
		var alojamientoComodidadesEliminarSet = new HashSet<AlojamientoComodidadAlojamiento>();

		jpa.getIdAlojamientoComodidades().forEach(alojamientoComodidadAlojamiento -> {
			if (!dto.getIdComodidades().contains(alojamientoComodidadAlojamiento.getIdComodidadAlojamiento().getId())) {
				this.alojamientoComodidadAlojamientoRepository.delete(alojamientoComodidadAlojamiento);
				alojamientoComodidadesEliminarSet.add(alojamientoComodidadAlojamiento);
			}
		});

		alojamientoComodidadesSet.removeAll(alojamientoComodidadesEliminarSet);

		dto.getIdComodidades().forEach(comodidadId -> {
			if (!this.alojamientoComodidadAlojamientoRepository
					.findByIdAlojamientoIdAndIdComodidadAlojamientoId(jpa.getId(), comodidadId).isPresent()) {
				var jpaComodidad = this.comodidadAlojamientoRepository.findById(comodidadId)
						.orElseThrow(() -> new RuntimeException("Comodidad no encontrada"));
				var alojamientoComodidadAlojamiento = AlojamientoComodidadAlojamiento.builder().idAlojamiento(jpa)
						.idComodidadAlojamiento(jpaComodidad).build();
				alojamientoComodidadesSet.add(alojamientoComodidadAlojamiento);
			}
		});

		jpa.setIdAlojamientoComodidades(alojamientoComodidadesSet);

		// MODIFICAR IMAGENES ALOJAMIENTO
		var imagenesList = new HashSet<ImagenAlojamiento>();
		dto.getIdImagenesAlojamiento().forEach(imagen -> {
			if (imagen != null) {
				var imagenJpa = new ImagenAlojamiento();
				if (imagen.getId() != null) {
					imagenJpa = this.imagenAlojamientoRepository.findById(imagen.getId())
							.orElseThrow(() -> new RuntimeException("Imagen no encontrada"));
					imagenJpa.setNumOrden(imagen.getNumOrden());
					imagenJpa.setUrlDatosImagen(imagen.getUrlDatosImagen());
				} else {
					imagenJpa = ImagenAlojamiento.builder().urlDatosImagen(imagen.getUrlDatosImagen())
							.numOrden(imagen.getNumOrden()).idAlojamiento(jpa).build();
				}
				imagenesList.add(imagenJpa);
			}
		});
		jpa.setIdImagenesAlojamiento(imagenesList);

		this.alojamientoRepository.save(jpa);

		return GenericAPIMessageDTO.builder().estado(HttpStatus.OK).mensaje("¡Alojamiento modificado con éxito!")
				.fechaYHora(LocalDateTime.now()).build();
	}
	
	/**
	 * Método que sirve para eliminar un alojamiento, en caso
	 * de no ser el usuario propietario o no tener el perfil de administrador,
	 * no te dejará hacer nada.
	 * @param id
	 * @return
	 */
	public GenericAPIMessageDTO eliminarAlojamiento(Long id, Authentication autenticacion) {
		boolean isAdmin = autenticacion.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.anyMatch(authority -> CodPerfiles.PERFIL_ADMIN.name().equals(authority));
		
		var jpa = this.alojamientoRepository.getReferenceById(id);
		var usuarioAutenticado = usuarioService.obtenerUsuarioApp(autenticacion);

		if (usuarioAutenticado.getId() == jpa.getIdUsuario().getId() || isAdmin) {
			this.alojamientoRepository.delete(jpa);
			return GenericAPIMessageDTO.builder().estado(HttpStatus.OK).mensaje("¡Alojamiento eliminado con éxito!")
					.fechaYHora(LocalDateTime.now()).build();
		}
		return null;

	}

	/**
	 * Método que sirve para convertir un jpa de alojamiento
	 * en un DTO de alojamiento.
	 * @param jpa
	 * @return
	 */
	public AlojamientoDTO toDto(Alojamiento jpa) {
		var comodidadesList = new HashSet<AlojamientoComodidadAlojamientoDTO>();
		ImagenAlojamientoDTO[] imagenesArr = new ImagenAlojamientoDTO[10];

		jpa.getIdAlojamientoComodidades().forEach(comodidad -> {
			comodidadesList.add(alojamientoComodidadAlojamientoService.toDtoWithoutAlojamiento(comodidad));
		});

		jpa.getIdImagenesAlojamiento().forEach(imagen -> {
			imagenesArr[imagen.getNumOrden()] = this.imagenAlojamientoService.toDto(imagen);
		});

		var valoracionesList = new HashSet<ValoracionAlojamientoDTO>();
		AtomicReference<Double> valorPromedio = new AtomicReference<>(0.00);
		AtomicReference<Integer> numValoraciones = new AtomicReference<>(0);
		AtomicInteger index = new AtomicInteger(0);

		if (jpa.getIdValoracionesAlojamiento().isEmpty()) {
			valorPromedio.updateAndGet(v -> (v + 0));
		} else {
			jpa.getIdValoracionesAlojamiento().forEach(valoracion -> {
				if (index.get() == 0) {
					valorPromedio.updateAndGet(v -> (v + valoracion.getPuntuacion()));
					index.updateAndGet(i -> i + 1);
				} else {
					valorPromedio.updateAndGet(v -> (v + valoracion.getPuntuacion()) / 2);
					index.updateAndGet(i -> i + 1);
				}
				numValoraciones.updateAndGet(v -> (v + 1));
				valoracionesList.add(valoracionAlojamientoService.toDto(valoracion));
			});
		}

		var dto = AlojamientoDTO.builder().id(jpa.getId()).txtNombre(jpa.getTxtNombre())
				.txtDescripcion(jpa.getTxtDescripcion()).numPlazaMin(jpa.getNumPlazaMin())
				.numPlazaMax(jpa.getNumPlazaMax()).numPrecioNoche(jpa.getNumPrecioNoche())
				.idUbicacion(UbicacionAlojamientoDTO.builder().ciudad(jpa.getIdUbicacion().getCiudad())
						.provincia(jpa.getIdUbicacion().getProvincia())
						.lineaDireccion(jpa.getIdUbicacion().getLineaDireccion())
						.codigoPostal(jpa.getIdUbicacion().getCodigoPostal()).build())
				.idValoracionesAlojamiento(valoracionesList).valoracionPromedio(valorPromedio.get())
				.numValoraciones(numValoraciones.get()).idAlojamientoComodidades(comodidadesList)
				.idImagenesAlojamiento(Arrays.asList(imagenesArr))
				.idUsuario(UsuarioDTO.builder().username(jpa.getIdUsuario().getUsername())
						.urlImagenUsuario(jpa.getIdUsuario().getUrlImagenUsuario())
						.txtDescripcion(jpa.getIdUsuario().getTxtDescripcion())
						.build())
				.build();
		return dto;
	}

}
