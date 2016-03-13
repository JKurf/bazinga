package src.States;

import src.Ent.Entity;
import src.Graphics.Renderer;

interface IBattleState {
    void Init(Entity A, Entity B);
    void Execute();
    void Update(double elapsedTime);
    void Render(Renderer graphics, int n);
    String check();
}