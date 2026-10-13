@Service
public class JuegoService{
    @Autowired
    JuegoRepository juegoRepository;
    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    public List<Juego> obtenerTodos(){
        return juegoRepository.findAll();
    }

    public void guardarJugo(Juego juego, MultipartFile portada){
        String nombreArchivo = "default.png";

        if(!portada.isEmpty()){
            nombreArchivo = guardarImagenLocal(portada);
            //alternativa en la nube: Cloudinary o Storage de firebase
        }
        juego.setPortadaUrl(nombreArchivo);
        juegoRepository.save(juego);
    }

    public String guardarImagenLocal(MultipartFile portada){
        try{
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if(!Files.exists(uploadPath)){
                    Files.createDirectories(uploadPath);
                }

                String nombreArchivo = UUID.randomUUID().toString() + "_" + portada.getOriginalFilename();
                Path filePath = uploadPath.resolve(nombreArchivo);

                Files.copy(portada.getInputStream(), filePath);

                return nombreArchivo;
            } catch (IOException e){
                e.printStackTrace();
                return "default.png";
            }
    }

    public List<Juego> buscarPorTitulo(String palabra){
        if(palabra == null || palabra.trim().isEmpty()){
            return listarTodos();
        }
        return juegoRepository.findByTituloContainingIgnoreCase(palabra);
    }
}