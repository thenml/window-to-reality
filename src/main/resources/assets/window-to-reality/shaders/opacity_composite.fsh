#version 330

uniform sampler2D MainSampler;
uniform sampler2D MainDepthSampler;
uniform sampler2D OpacitySampler;
uniform sampler2D OpacityDepthSampler;

in vec2 texCoord;
out vec4 fragColor;

void main() {
    vec4 color = texture(MainSampler, texCoord);
    float mainDepth = texture(MainDepthSampler, texCoord).r;
    float opacityDepth = texture(OpacityDepthSampler, texCoord).r;

    float a = 1.0;
    if (opacityDepth >= mainDepth) {
    	a = 1.0 - texture(OpacitySampler, texCoord).a;
    }

    fragColor = vec4(color.rgb, a);
}