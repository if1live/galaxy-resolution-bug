using System.Text;
using UnityEngine;
using UnityEngine.UI;

public class Main : MonoBehaviour {
    public Text text;

    void Start() {
        Refresh();
    }

    private void Update() {
        if(Input.GetButtonDown("Fire1")) {
            Refresh();
        }
    }

    void Refresh() {
        var sb = new StringBuilder();

        sb.Append(Application.identifier);
        sb.AppendLine();

        var size = Screen.currentResolution;
        sb.AppendFormat("width: {0}", size.width);
        sb.AppendLine();
        sb.AppendFormat("Height: {0}", size.height);

        text.text = sb.ToString();
    }
}
