package com.algaworks.brewer.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {
	
	public String salvarTemporariamente(MultipartFile files[]);

	public byte[] recuperarFotoTemporaria(String nome);
	
	public byte[] recuperar(String foto);

	public void salvar(String foto);
	
	public String thumbnailPrefix();	

	public byte[] recuperarThumbnail(String fotoCerveja);

}
