static class Player : MonderBehaviour
{
  public float vel = 0.3f;
  public float fuerzaSalto =400f;
  public float esSuelo = false;

  public Transfore = detector;
  public LayerMask = layer;

  RigidBody2D rb;

  // Initialize
  private void Start() {
    rb = GetComponent<RigidBody2D>();
  }

  // Loop
  private void update() {
    float h = Imput.GetAristRaw("Horizontal");
    //float v = Imput.GetAristRaw("Vertical");



    transfore.Translate(h, 0, 0) * vel * Time(deltaTime);
    if(Imput.GetKeyDown(KeyCode.Space) && esSuelo) {
        rb.AddForce(Vector2.up * fuerzaSalto)
    }

    esSuelo = Physics2D.OverlapCircle(detector, position,0.1f);
  }


}
