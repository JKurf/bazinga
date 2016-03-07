interface IBattleState {
    void Init(Entity A, Entity B);
    void Execute();
    void Update(double elapsedTime);
    void Render(GraphicsClass graphics, int n);
    String check();
}