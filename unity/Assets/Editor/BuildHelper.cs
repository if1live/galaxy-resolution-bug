using UnityEditor;
using UnityEngine;

public class BuildHelper : MonoBehaviour {
    [MenuItem("Game/idenfitier_com.fiveminlab.toyclash")]
    static void SetAs_com_fiveminlab_toyclash() {
        var id = "com.fiveminlab.toyclash";
        SetIdentifier(id);
    }

    [MenuItem("Game/idenfitier_com.fiveminlab.Toyclash")]
    static void SetAs_com_fiveminlab_Toyclash() {
        var id = "com.fiveminlab.Toyclash";
        SetIdentifier(id);
    }

    static void SetIdentifier(string id) {
        PlayerSettings.applicationIdentifier = id;
        Debug.LogFormat("identifier : {0}", id);
    }
}
